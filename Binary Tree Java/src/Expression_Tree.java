import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Nicholas Zukaitis
 * Expression tree composed of nodes that represents an infix expression
 */
public class Expression_Tree {
    /**
     * Infix expression that the expression tree is representing
     */
    private String infixExpression;
    /**
     * Postfix expression that is used to generate an expression tree
     */
    private String postfixExpression;
    /**
     * Prefix expression that is converted from postfix
     */
    private  String prefixExpression;
    /**
     * The root of the expression tree
     */
    private Node<String> root;

    /**
     * Default constructor for the Expression_Tree class, it does nothing in this class
     */
    public Expression_Tree(){
        //default constructor does nothing in this class
    }

    /**
     * Overloaded constructor for the Expression_Tree class, it generates an expression tree from an infix expression
     * @param expression The infix expression that the expression tree is built off of
     */
    public Expression_Tree(String expression){
        infixExpression = expression;

        postfixExpression = infixToPostfix(infixExpression);
        prefixExpression = postfixToPrefix(postfixExpression);
        root = buildExpressionTree(postfixExpression);
    }

    /**
     * Gets the infix expression represented by the expression tree
     * @return An infix expression
     */
    public String getInfixExpression() {
        return infixExpression;
    }

    /**
     * Gets the root node of the expression tree
     * @return Root node of the expression tree
     */
    public Node<String> getRoot() {
        return root;
    }

    /**
     * Converts an infix expression to a postfix expression
     * @param infixExpression An infix expression
     * @return The postfix expression converted from infixExpression
     */
    private String infixToPostfix(String infixExpression){
        String result = "";
        Stack<Character> stack = new Stack<Character>();

        for(int i = 0; i < infixExpression.length(); i++){
            Character currentChar = infixExpression.charAt(i);

            if(Character.isDigit(currentChar)){
                result += currentChar;
            }

            else{
                //insert spaces to separate numbers, this makes it easier to properly assign values to the nodes of an expression tree
                if(i > 0 && Character.isDigit(infixExpression.charAt(i-1))){
                    result += " ";
                }
                if(currentChar == '('){
                    stack.push(currentChar);
                }

                else if(currentChar == ')'){
                    while(!stack.isEmpty() && stack.peek() != '('){
                        result += stack.pop();
                    }
                    stack.pop();
                }

                else {
                    while (!stack.isEmpty() && precedence(currentChar) <= precedence(stack.peek())) {
                        result += stack.pop();
                    }
                    stack.push(currentChar);
                }
            }
        }

        while(!stack.isEmpty()){
            result += stack.pop();
        }
        return result;
    }

    /**
     * Converts a postfix expression to a prefix expression
     * @param postfixExpression A postfix expression
     * @return The prefix expression converted from postfixExpression
     */
    private String postfixToPrefix(String postfixExpression){
        String operand2 = "";
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < postfixExpression.length(); i++){
            if(Character.isDigit(postfixExpression.charAt(i))) continue;

            else{
                if(Character.isDigit(postfixExpression.charAt(i-1))){
                    for(int j = i-1; j >= 0; j--){
                        if(Character.isDigit(postfixExpression.charAt(j)) && j != 0) continue;

                        else if(Character.isDigit(postfixExpression.charAt(j)) && j == 0){
                            stack.push(postfixExpression.substring(j,i));
                            break;
                        }

                        else{
                            stack.push(postfixExpression.substring(j+1,i));
                            break;
                        }
                    }
                }

                if(postfixExpression.charAt(i) == ' ') continue;

                else{
                    operand2 = stack.pop();
                    String temp = postfixExpression.charAt(i) + stack.pop() + operand2;
                    stack.push(temp);
                }
            }
        }
        return stack.pop();
    }

    /**
     * Determines the precedence of a given operator, used for the infixToPostfix() method
     * @param operator Mathematic operator (+,-,*,/)
     * @return The precedence of the operator (1 for + and -, 2 for * and /). Returns -1 if a non-operator string is passed in
     */
    private int precedence(char operator){
        switch(operator){
            case('+'):
            case('-'):
                return 1;
            case('*'):
            case('/'):
                return 2;
            default:
                return -1;
        }
    }

    /**
     * Generated an expression tree from a postfix expression
     * @param postfixExpression The postfix expression used to generate an expression tree
     * @return The root node of an expression tree generated from postfixExpression
     */
    private Node<String> buildExpressionTree(String postfixExpression){
        Stack<Node<String>> stack = new Stack<Node<String>>();
        for(int i = 0; i < postfixExpression.length(); i++){
            if(Character.isDigit(postfixExpression.charAt(i))) continue;

            else{
                if(Character.isDigit(postfixExpression.charAt(i-1))){
                    for(int j = i-1; j >= 0; j--){
                        if(Character.isDigit(postfixExpression.charAt(j)) && j != 0) continue;

                        else if(Character.isDigit(postfixExpression.charAt(j)) && j == 0){
                            stack.push(new Node<String>(postfixExpression.substring(j,i)));
                            break;
                        }

                        else{
                            stack.push(new Node<String>(postfixExpression.substring(j+1,i)));
                            break;
                        }
                    }
                }

                if(postfixExpression.charAt(i) == ' ') continue;

                else{
                    stack.push(new Node<String>(Character.toString(postfixExpression.charAt(i)), stack.pop(), stack.pop()));
                }
            }
        }
        return stack.pop();
    }

    /**
     * Traverses through a binary tree using the preorder algorithm
     * @param root The root of a binary tree
     * @param list A linkedList that stores the order of traversed nodes
     * @return A linkedList containing all the nodes of a binary tree in the order they were traversed
     */
    public LinkedList<Node> preorderTraversal(Node root, LinkedList<Node> list){
        if(root == null){
            return null;
        }

        list.add(root);
        preorderTraversal(root.getLeftChild(), list);
        preorderTraversal(root.getRightChild(), list);

        return list;
    }

    /**
     * Traverses through a binary tree using the inorder algorithm
     * @param root The root of a binary tree
     * @param list A linkedList that stores the order of traversed nodes
     * @return A linkedList containing all the nodes of a binary tree in the order they were traversed
     */
    public LinkedList<Node> inorderTraversal(Node root, LinkedList<Node> list){
        if(root == null){
            return null;
        }

        inorderTraversal(root.getLeftChild(), list);
        list.add(root);
        inorderTraversal(root.getRightChild(), list);

        return list;
    }

    /**
     * Traverses through a binary tree using the postorder algorithm
     * @param root The root of a binary tree
     * @param list A linkedList that stores the order of traversed nodes
     * @return A linkedList containing all the nodes of a binary tree in the order they were traversed
     */
    public LinkedList<Node> postorderTraversal(Node root, LinkedList<Node> list){
        if(root == null){
            return null;
        }

        postorderTraversal(root.getLeftChild(), list);
        postorderTraversal(root.getRightChild(), list);
        list.add(root);

        return list;
    }

    /**
     * Overridden equals() for the Expression_Tree class, uses a linked list generated by preorderTraversal() to compare with another expression tree
     * @param o The root node of another expression tree
     * @return If the two trees have the same nodes in the same locations
     */
    public boolean equals(Object o){
        //two trees are equal if they have the same nodes in the same locations,
        //so if traversing them in the same way produces identical results, they should be equal
        LinkedList<Node> comparison = preorderTraversal(root, new LinkedList<Node>());
        return comparison.equals(preorderTraversal((Node) o, new LinkedList<Node>()));
    }

    /**
     * Overridden toString() for the Expression_Tree class, a string representation of an expression tree
     * @return The type of tree, the infix expression the tree represents, the prefix form of that expression and the postfix form of that expression
     */
    public String toString(){
        /**
         * This is the word-for-word description for this class' toString for this project
         * I'm not 100% sure what the desired representation of this tree is,
         * so I will be following the description as exactly as I can
         *
         * A string representation of this expression tree should be returned from this method. For this assignment, the following
         * information about this tree should be included in the returned string:
         * • the type of this tree
         * • the infix expression of this tree
         * • the prefix form of this tree
         * • the postfix form of this tree
         */

        String string = "Expression Tree\n";
        string += infixExpression + "\n";
        string += prefixExpression + '\n';
        string += postfixExpression;
        return string;
    }
}
