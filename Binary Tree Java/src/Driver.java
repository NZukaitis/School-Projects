import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Driver {
    public static void main(String[] args){
        try{
            List<String> lines = Files.readAllLines(Paths.get("test_data.txt"));
            Iterator<String> iterator = lines.iterator();
            Expression_Tree expression_tree;

            while(iterator.hasNext()){
                expression_tree = new Expression_Tree(iterator.next());

                System.out.println(expression_tree);
                System.out.print("Preorder: ");
                System.out.println(expression_tree.preorderTraversal(expression_tree.getRoot(), new LinkedList<Node>()));
                System.out.print("Inorder: ");
                System.out.println(expression_tree.inorderTraversal(expression_tree.getRoot(), new LinkedList<Node>()));
                System.out.print("Postorder: ");
                System.out.println(expression_tree.postorderTraversal(expression_tree.getRoot(), new LinkedList<Node>()));
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
