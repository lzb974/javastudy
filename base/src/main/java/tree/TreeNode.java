package tree;

/**
 * @Description
 * @Author lzb
 * @Date 2021/6/24 17:36
 * @Version 1.0
 */
public class TreeNode {
    String value = null;
    TreeNode leftchildren = null;
    TreeNode rightchildre = null;

    public TreeNode(String value, TreeNode leftchildren, TreeNode rightchildre) {
        this.value = value;
        this.leftchildren = leftchildren;
        this.rightchildre = rightchildre;
    }

    public TreeNode(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLeftchildren(TreeNode leftchildren) {
        this.leftchildren = leftchildren;
    }

    public void setRightchildre(TreeNode rightchildre) {
        this.rightchildre = rightchildre;
    }

    public String getValue() {
        return value;
    }

    public TreeNode getLeftchildren() {
        return leftchildren;
    }

    public TreeNode getRightchildre() {
        return rightchildre;
    }
}
