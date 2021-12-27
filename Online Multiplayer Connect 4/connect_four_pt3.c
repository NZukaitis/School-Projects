//Project Partners: Nicholas Zukaitis, Jona Klinowski

#define _GNU_SOURCE     //To get NI_MAXHOST
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>
#include <ifaddrs.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int boardWidth = 7;
int boardHeight = 6;
int **board; //Each position on the board has 3 possible states 0: empty, 1: 'owned' by player 1, -1; 'owned' by player 2

char move;

char * player1;
char * player2;

char * p1turnTxt;
char * p2turnTxt;

int turn = 1; //1 is player1's turn -1 is player2's turn

int gameStillActive = 1; //variable controlling main gameplay loop. set to 0 once game is won

struct ifaddrs *ifaddr;
int family;
int s;
char ip[NI_MAXHOST];
int serverfd, clientfd, size;
struct addrinfo hints, *servinfo;
struct sockaddr_storage their_addr;

int isServer;

char validMove(char move);

void getIP(){
    if(getifaddrs(&ifaddr) == -1){
            exit(1);
        }

    for(struct ifaddrs *ifa = ifaddr; ifa != NULL; ifa = ifa->ifa_next){
        if(ifa->ifa_addr == NULL){
            continue;
        }
         family = ifa->ifa_addr->sa_family;

        if (family == AF_INET && ((ifa->ifa_name)[0] == 'e' || (ifa->ifa_name)[0] == 'w')) { //finds the first listed IPv4 address, eithar eth or wlan
            s = getnameinfo(ifa->ifa_addr, sizeof(struct sockaddr_in),
            ip, NI_MAXHOST,
            NULL, 0, NI_NUMERICHOST);
            break;
        }    
    }
}

int getVersion(char **info){
    if((strlen(info[1]) <= 4)){
        return 1;
    }
    else{
        return 0;
    }
}

void serverInit(char *port){
    getIP();

    char message[6];

    hints.ai_addrlen = strlen(ip);

    getaddrinfo(ip, port, &hints, &servinfo);

    if((serverfd = socket(PF_INET, SOCK_STREAM, 0)) == -1){
        exit(1);
    }

    if((bind(serverfd, servinfo->ai_addr, servinfo->ai_addrlen)) == -1){
        exit(1); 
    }

    if((listen(serverfd, 5)) == -1){
        exit(1);
    }

    size = sizeof(their_addr);
    clientfd = accept(serverfd, (struct sockaddr *)&their_addr, &size);

    printf("Name limit is 50 characters\n");
    printf("Enter player 1's name: ");
    scanf("%s", player1);

    write(clientfd, "Name limit is 50 characters\nEnter player 2's name: ", 55);

    printf("Player 2 choosing name\n");

    read(clientfd, player2, 50);

    board = malloc(boardHeight*sizeof(int *));
    for(int i = 0; i < boardWidth; i++){
        board[i] = malloc(boardWidth*sizeof(int));
    }

    for(int i = 0; i < boardHeight; i++){ //filling board with 'empty' spaces
        for(int j = 0; j < boardWidth; j++){
            board[i][j] = 0;
        }
    }
}

void clientInit(char *addr, char *port){
    char playerIntro[55];
    char playerName[50];
            hints.ai_addrlen = strlen(addr);

            getaddrinfo(addr, port, &hints, &servinfo);

            if((clientfd = socket(PF_INET, SOCK_STREAM, 0)) == -1){
                exit(1);
            }

            if((connect(clientfd, servinfo->ai_addr, servinfo->ai_addrlen)) == -1){
                exit(1);
            }

            printf("Player 1 choosing name\n");

            read(clientfd, playerIntro, 55);
            printf("%s\n", playerIntro);

            scanf("%s", playerName);
            write(clientfd, playerName, 50);
}

int makeMove(int position){
    for(int i = boardHeight-1; i >= 0; i--){
        if(board[i][position] != 0){
            if(i == 5){
                printf("Column already full, try again\n");
                return -1;
            }
            else{
                board[i+1][position] = turn;
                return 0;
            }
        }
        else if(i == 0 && board[i][position] == 0){
            board[i][position] = turn;
            return 0;
        }
    }
}

int checkWin(){ //returns 0 if not won, returns 1 if won
   if(isServer){
        int count = 0;

        for(int i = 0; i < boardHeight; i++){
            count = 0;

            for(int j = 0; j < boardWidth; j++){
                count = 0;

                if(i >= 3){ //top half
                    if(j < 4){
                        for(int k = 1; k < 4; k++){
                            if(board[i][j] == board[i][j+k] && board[i][j] != 0){
                                count++;
                            }
                        }

                        if(count == 3){
                            return 1;
                        }
                    }
                }
                else{ //bottom half
                    if(j <= 2){ //left
                        for(int k = 1; k < 4; k++){ //vertical
                            if(board[i][j] == board[i+k][j] && board[i][j] != 0){
                                count++;
                            }
                        }
                        if(count == 3){
                            return 1;
                        }

                        count = 0;

                        for(int k = 1; k < 4; k++){//diagonal right
                            if(board[i][j] == board[i+k][j+k] && board[i][j] != 0){
                                count++;
                            }
                        }
                        if(count == 3){
                            return 1;
                        }

                        count = 0;

                        for(int k = 1; k < 4; k++){//horizontal
                            if(board[i][j] == board[i][j+k] && board[i][j] != 0){
                                count++;
                            }
                        }
                        if(count == 3){
                            return 1;
                        }
                    }
                    else if(j == 3){ //mid
                        for(int k = 1; k < 4; k++){ //vertical
                            if(board[i][j] == board[i+k][j] && board[i][j] != 0){
                                count++;
                            }
                        }
                        if(count == 3){
                            return 1;
                        }

                        count = 0;

                        for(int k = 1; k < 4; k++){//diagonal right
                            if(board[i][j] == board[i+k][j+k] && board[i][j] != 0){
                                count++;
                            }
                        }
                        if(count == 3){
                            return 1;
                        }

                        count = 0;

                        for(int k = 1; k < 4; k++){//diagonal left
                            if(board[i][j] == board[i+k][j-k] && board[i][j] != 0){
                                count++;
                            }
                        }
                        if(count == 3){
                            return 1;
                        }

                        count = 0;


                        for(int k = 1; k < 4; k++){//horizontal
                            if(board[i][j] == board[i][j+k] && board[i][j] != 0){
                                count++;
                            }
                        }
                        if(count == 3){
                            return 1;
                        }
                    }
                    else{//right
                        for(int k = 1; k < 4; k++){ //vertical
                            if(board[i][j] == board[i+k][j] && board[i][j] != 0){
                                count++;
                            }
                        }
                        if(count == 3){
                            return 1;
                        }

                        count = 0;

                        for(int k = 1; k < 4; k++){//diagonal left
                            if(board[i][j] == board[i+k][j-k] && board[i][j] != 0){
                                count++;
                            }
                        }
                        if(count == 3){
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
   }
}

void init(char **info){
    printf("Setting up the game\n");

    player1 = malloc(50*sizeof(char));
    player2 = malloc(50*sizeof(char));

    p1turnTxt = malloc(60*sizeof(char));
    p2turnTxt = malloc(60*sizeof(char));

    memset(&hints, 0, sizeof(hints));
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_family = AF_INET;

    isServer = getVersion(info);

    if(isServer){
        serverInit(info[1]);
    }
    else{
        clientInit(info[1], info[2]);
    }

    strcpy(p1turnTxt, player1);
    strcpy(p2turnTxt, player2);
    strcat(p1turnTxt, "'s turn\n");
    strcat(p2turnTxt, "'s turn\n");
}

void printBoard(){
    char printRow[7];
    int zeroCounter = 0;
    char clientPrint[7];
    if(isServer){
        for(int i = boardHeight-1; i >= 0; i--){
            for(int j = 0; j < boardWidth; j++){
                switch(board[i][j]){
                    case 0:
                        printRow[j] = '0';
                        zeroCounter++;
                        break;
                    case 1:
                        printRow[j] = '1';
                        break;
                    case -1:
                        printRow[j] = '2';
                        break;
                    default:
                        printf("Something went wrong\n");
                        break;
                }
            }
        printf("%s\n", printRow);
        write(clientfd, printRow, 7);
        }

        if(zeroCounter == 0 && gameStillActive){
            printf("Draw\n");
            gameStillActive = 0;
        }
    }
    else{
        for(int i = boardHeight-1; i >= 0; i--){
            read(clientfd, clientPrint, 7);
            printf("%s\n", clientPrint);
        }
    }
    
}

char input(){
    
    char clientTxt[75];
    if(gameStillActive){
        if(isServer){
            if(turn > 0){
                printf("%s's turn\n", player1);
                write(clientfd, p1turnTxt, 60);

                printf("Enter a move: ");
                move = getchar(); //idk why, but it only works when I call getchar() twice
                move = getchar();

                if((move = validMove(move)) == 0){
                    write(clientfd, &move, 1);
                    input();
                }
                else{
                    return move;
                }
            }
            else{
                printf("%s's turn\n", player2);
                write(clientfd, p2turnTxt, 75);
                read(clientfd, &move, 1);
                if((move = validMove(move)) == 0){
                    write(clientfd, "0", 1);
                    input();
                }
                else{
                    write(clientfd, &move, 1);
                }
            }  
        }

        else{ //all the client does is return info to the server
            if(turn > 0){
                read(clientfd, clientTxt, 60);
                printf("%s", clientTxt);
                read(clientfd, &move, 1);
                if(move == 0){
                    input();
                }
            }
            else{
                read(clientfd, clientTxt, 75);
                printf("%s\nEnter a move: ", clientTxt);
                scanf("%s", &move);
                write(clientfd, &move, 1);
                read(clientfd, &move, 1);

                if(move== '0'){
                    printf("Invalid move\n");
                    input();
                }
                else{
                    write(clientfd, &move, 1);
                }
                
            }
            return '\0';
        }
    }
}

char validMove(char move){
    //inputs are case insensitive, the quit character is q
            switch(move){
                case 'A':
                    if(makeMove(0) >= 0){
                        return move;
                    }
                    break;
                case 'B':
                    if(makeMove(1) >= 0){
                        return move;
                    }
                    break;
                case 'C':
                    if(makeMove(2) >= 0){
                        return move;
                    }
                    break;
                case 'D':
                    if(makeMove(3) >= 0){
                        return move;
                    }
                    break;
                case 'E':
                    if(makeMove(4) >= 0){
                        return move;
                    }
                    break;
                case 'F':
                    if(makeMove(5) >= 0){
                        return move;
                    }
                    break;
                case 'G':
                    if(makeMove(6) >= 0){
                        return move;
                    }
                    break;
                case 'Q':
                    return move;
                case 'a':
                    if(makeMove(0) >= 0){
                        return move;
                    }
                    break;
                case 'b':
                    if(makeMove(1) >= 0){
                        return move;
                    }
                    break;
                case 'c':
                    if(makeMove(2) >= 0){
                        return move;
                    }
                    break;
                case 'd':
                    if(makeMove(3) >= 0){
                        return move;
                    }
                    break;
                case 'e':
                    if(makeMove(4) >= 0){
                        return move;
                    }
                    break;
                case 'f':
                    if(makeMove(5) >= 0){
                        return move;
                    }
                    break;
                case 'g':
                    if(makeMove(6) >= 0){
                        return move;
                    }
                    break;
                case 'q':
                    return move; 
                default:
                    printf("Invalid move\n");
                    return 0;
            }
}

void update(char move){
    if(move == 'Q' || move == 'q'){
        gameStillActive = 0;
    }

    else{
        if(checkWin()){
            gameStillActive = 0;
            printBoard();

            if(turn > 0){
                printf("%s wins\n", player1);
            }
            else{
                printf("%s wins\n", player2);
            }
        }   
    }

    if(!gameStillActive){
        if(isServer){
            write(clientfd, "\0", 1);
        }
    }
    else{
        if(isServer){
            write(clientfd, "\r", 1);
            
        }
        else{
            char active;
            read(clientfd, &active, 1);
            if(active == '\0'){
                gameStillActive = 0;
                printf("pp\n");
                return;
            }
        }
        turn *= -1;
    }
    
}

void display(){
    if(gameStillActive){
        printBoard();
    }
    
}

void teardown(){
    printf("Destroying the game\n");

    for(int i = 0; i < boardWidth; i++){//freeing every array in the 2d array
        free(board[i]);
    }

    free(board);
    free(player1);
    free(player2);
    free(p1turnTxt);
    free(p2turnTxt);

    exit(0);
}

int main(int argc, char ** argv){
    char move;

    init(argv);
    while(gameStillActive){
        move = input();
        update(move);
        display();
    }
    teardown();
}