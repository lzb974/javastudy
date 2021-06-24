package tree;

import java.util.LinkedList;

/**
 * @Description
 * @Author lzb
 * @Date 2021/6/24 17:38
 * @Version 1.0
 */
public class TreeSearch {
    // 创建一个二叉树
    public TreeNode getTargetTree() {
        // 叶子节点
        TreeNode G = new TreeNode("G");
        TreeNode D = new TreeNode("D");
        TreeNode E = new TreeNode("E", G, null);
        TreeNode B = new TreeNode("B", D, E);
        TreeNode H = new TreeNode("H");
        TreeNode I = new TreeNode("I");
        TreeNode F = new TreeNode("F", H, I);
        TreeNode C = new TreeNode("C", null, F);
        // 构造根节点
        TreeNode root = new TreeNode("A", B, C);
        return root;
    }

    /**
     * 前序遍历
     */
    public void preorderVistTreeNode(TreeNode node) {
        if (null != node) {
            //核心打印
            System.out.print(node.value);
            if (null != node.leftchildren) {
                preorderVistTreeNode(node.leftchildren);
            }
            if (null != node.rightchildre) {
                preorderVistTreeNode(node.rightchildre);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inorderVistTreeNode(TreeNode node) {
        if (null != node) {
            if (null != node.leftchildren) {
                inorderVistTreeNode(node.leftchildren);
            }
            //核心打印
            System.out.print(node.value);
            if (null != node.rightchildre) {
                inorderVistTreeNode(node.rightchildre);
            }
        }
    }

    /**
     * 后序遍历
     */
    public void postorderVistTreeNode(TreeNode node) {
        if (null != node) {
            if (null != node.leftchildren) {
                postorderVistTreeNode(node.leftchildren);
            }
            if (null != node.rightchildre) {
                postorderVistTreeNode(node.rightchildre);
            }
            //核心打印
            System.out.print(node.value);
        }
    }

    /**
     * 层次遍历
     */
    public void levelorderVistTreeNode(TreeNode node) {
        if (null != node) {
            LinkedList<TreeNode> list = new LinkedList<TreeNode>();
            list.add(node);
            TreeNode currentNode;
            while (!list.isEmpty()) {
                currentNode = list.poll(); //获取并移除此列表的头
                //核心打印
                System.out.print(currentNode.value);
                if (null != currentNode.leftchildren) {
                    list.add(currentNode.leftchildren);
                }
                if (null != currentNode.rightchildre) {
                    list.add(currentNode.rightchildre);
                }
            }
        }
    }

    public static void main(String[] args) {

        TreeSearch treeSearch = new TreeSearch();
        TreeNode tree = treeSearch.getTargetTree();

        System.out.print("遍历:");
        treeSearch.levelorderVistTreeNode(tree);
        System.out.println("");
    }

}
