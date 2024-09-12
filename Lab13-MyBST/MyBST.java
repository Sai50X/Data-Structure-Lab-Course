import java.util.*;
import org.w3c.dom.Node;

public class MyBST
{
    public BSTNode overallRoot;

    public MyBST() {
        overallRoot = null;
    }
    
    private class BSTNode {
        public Integer val;
        public BSTNode left, right;
     
        public BSTNode(Integer val) {
           this.val = val;
           left = right = null;
        }
     
        @Override
        public String toString() { 
           return "" + this.val;
         }
     }

    
    public void insert(Integer n) {
        if (overallRoot == null) {
            overallRoot = new BSTNode(n);
        } else {
            iHelper(overallRoot, n);
        }
    }

    private void iHelper(BSTNode node, Integer n)
    {
        if (n > node.val) { // go right cuz bigger than current
            if (node.right == null) {
                node.right = new BSTNode(n);
            } else {
                iHelper(node.right, n);
            }
        } else { //go left cuz smaller than current
            if (node.left == null) {
                node.left = new BSTNode(n);
            } else {
                iHelper(node.left, n);
            }
        }
    }

    public int size() {
        return sHelper(overallRoot);
    }

    private int sHelper(BSTNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sHelper(node.left) + sHelper(node.right);
        }
    }

    public boolean contains(Integer n)
    {
        if (overallRoot == null) {
            return false;
        } else {
            return cHelper (n, overallRoot);
        }
    }

    private boolean cHelper(Integer n, BSTNode node) {
        if (n > node.val) { // go right cuz bigger than current
            if (node.right == null) {
                return false;
            } else {
                return cHelper(n, node.right);
            }
        } else if (n < node.val) { // go left cuz smaller than current
            if (node.left == null) {
                return false;
            } else {
                return cHelper(n, node.left);
            }
        } else {
            return true;
        }
    }

    public Integer getMax() {
        if (overallRoot == null) {
            return -1;
        } else {
            return maxHelper(overallRoot);
        }
    }

    private Integer maxHelper(BSTNode node) {
        if (node.right != null) {
            return maxHelper(node.right);
        } else {
            return node.val;
        }
    }

    public Integer getMin() {
        if (overallRoot == null) {
            return -1;
        } else {
            return minHelper(overallRoot);
        }
    }

    private Integer minHelper(BSTNode node) {
        if (node.left != null) {
            return minHelper(node.left);
        } else {
            return node.val;
        }
    }

    public void delete(Integer n) {
        if (overallRoot != null) {
            dHelper(overallRoot, n, null, false);
        }
    }

    private void dHelper(BSTNode node, Integer n, BSTNode parent, boolean left) {
        if (node.val == n)
        {
            if (node.right == null && node.left == null)
            {
                if (parent == null)
                {
                    overallRoot = null;
                    return;
                }

                if (left)
                {
                    parent.left = null;
                }
                else
                {
                    parent.right = null;
                }
            }
            else if (node.right != null && node.left != null)
            {
                    BSTNode temp1 = node;
                    BSTNode temp2 = node.right;
                    while (temp2.left != null)
                    {
                        temp1 = temp2;
                        temp2 = temp2.left;
                    }
                    if (temp1 != node)
                        temp1.left = temp2.right;
                    else
                        temp1.right = temp2.right;
        
                    node.val = temp2.val;
            }
            else if (node.right == null)
            {
                if (parent == null)
                {
                    overallRoot = node.left;
                }
                else if (left)
                {
                    parent.left = node.left;
                }
                else
                {
                    parent.right = node.left;
                }
            }
            else if (node.left == null)
            {
                if (parent == null)
                {
                    overallRoot = node.right;
                }
                else if (left)
                {
                    parent.left = node.right;
                }
                else
                {
                    parent.right = node.right;
                }
            }
        }
        if (n < node.val) {
            if (node.left == null) {
                return;
            } else {
                dHelper(node.left, n, node, true);
            }
        } else {
            if (node.right == null) {
                return;
            } else {
                dHelper(node.right, n, node, false);
            }
        }
    } // yeah no never doing this again

    public void inOrder() {
        oHelper(overallRoot);
    }

    private void oHelper(BSTNode node)
    {
        if (node == null) {
            return;
        } else {
           oHelper(node.left);
           System.out.print(node.val+ " ");
           oHelper(node.right);
        }
    }

    public void print() {
        pHelper(overallRoot, 0);
    }

    private void pHelper(BSTNode node, int level) {
        if (node == null) {
            return;
        } else {
           pHelper (node.right, level+1);
           for (int i = 0; i < level; i++) {
              System.out.print("    ");
           }
           System.out.println(node.val);
           pHelper(node.left, level+1);
        }
    }  
}