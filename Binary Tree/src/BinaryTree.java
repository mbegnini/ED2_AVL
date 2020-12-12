import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

	private Node root;

	public BinaryTree() {
		super();
		root = null;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void insertNode(Data data) {
		this.root = insertNode(root, data);
	}

	private Node insertNode(Node currentNode, Data data) {
		if (currentNode == null)
			return new Node(data);
		if (currentNode.getData().getId() >= data.getId())
			currentNode.setLeft(insertNode(currentNode.getLeft(), data));
		else
			currentNode.setRight(insertNode(currentNode.getRight(), data));

		return rebalance(currentNode);
	}

	public Data getData(int id) {
		return getData(root, id);
	}

	private Data getData(Node currentNode, int id) {
		if (currentNode == null)
			return null;
		else if (currentNode.getData().getId() == id)
			return currentNode.getData();
		else if (currentNode.getData().getId() > id)
			return getData(currentNode.getLeft(), id);
		else
			return getData(currentNode.getRight(), id);
	}
	
	public void removeNode(int id) {
		this.root = removeNode(this.root, id);
	}
	
	private Node removeNode(Node currentNode, int id) {
		if(currentNode == null)
			return null;
		
		if(id == currentNode.getData().getId()) {
			if(currentNode.getLeft() == null && currentNode.getRight() == null)
				return null;
			else if(currentNode.getLeft() == null)
				return currentNode.getRight();
			else if(currentNode.getRight() == null)
				return currentNode.getLeft();
			else {
				Data data = getDataFromRightMost(currentNode.getLeft());
				currentNode.setData(data);
				currentNode.setLeft(removeNode(currentNode.getLeft(), data.getId()));
			}
		}else if (id < currentNode.getData().getId()) {
			currentNode.setLeft(removeNode(currentNode.getLeft(), id));
		}else {
			currentNode.setRight(removeNode(currentNode.getRight(), id));
		}
		if(currentNode!=null)
			return rebalance(currentNode);
		return currentNode;
	}
	
	private Data getDataFromRightMost(Node currentNode) {
		if(currentNode.getRight() == null)
			return currentNode.getData();
		return getDataFromRightMost(currentNode.getRight());
	}

	public void preOrder() {
		preOrder(root);
	}
	
	private void preOrder(Node currentNode) {
		if(currentNode == null)
			return;
		System.out.println(currentNode.getData().getId());
		preOrder(currentNode.getLeft());
		preOrder(currentNode.getRight());
	}
	
	public void inOrder() {
		inOrder(root);
	}
	
	private void inOrder(Node currentNode) {
		if(currentNode == null)
			return;
		inOrder(currentNode.getLeft());
		System.out.println(currentNode.getData().getId());
		inOrder(currentNode.getRight());
	}
	
	public void posOrder() {
		posOrder(root);
	}
		
	private void posOrder(Node currentNode) {
		if(currentNode == null)
			return;
		posOrder(currentNode.getLeft());
		posOrder(currentNode.getRight());
		System.out.println(currentNode.getData().getId());
	}
	
	public void reverseInOrder() {
		reverseInOrder(root);
	}
	
	private void reverseInOrder(Node currentNode) {
		if(currentNode == null)
			return;
		reverseInOrder(currentNode.getRight());
		System.out.println(currentNode.getData().getId());
		reverseInOrder(currentNode.getLeft());
	}
	
	public void levelOrder() {
		if (root == null)
			return;

		Queue<Node> nodes = new LinkedList<Node>();
		nodes.add(root);
		while(!nodes.isEmpty()) {
			Node node = nodes.remove();
			System.out.print(node.getData().getId() + " ");
			if(node.getLeft() != null)
				nodes.add(node.getLeft());
			if(node.getRight() != null)
				nodes.add(node.getRight());
		}
	}
	
	private int getNodeHeight(Node currentNode) {
		if (currentNode == null)
			return -1;
		return currentNode.getHeight();
	}

	private void updateNodeHeight(Node currentNode) {
		currentNode.setHeight(1+Math.max(getNodeHeight(currentNode.getLeft()), getNodeHeight(currentNode.getRight())));
	}
	
	private int getBalance(Node currentNode) {
		if (currentNode == null)
			return 0;
		return getNodeHeight(currentNode.getRight())-getNodeHeight(currentNode.getLeft());
	}
	
	private Node rebalance(Node currentNode) {
		updateNodeHeight(currentNode);
		int balance = getBalance(currentNode);
		if(balance > 1) {
			if(getBalance(currentNode.getRight())>0) {
				currentNode = rotateLeft(currentNode);
			}else {
				currentNode.setRight(rotateRight(currentNode.getRight()));
				currentNode = rotateLeft(currentNode);
			}
		}else if(balance < -1){
			if(getBalance(currentNode.getLeft())<0) {
				currentNode = rotateRight(currentNode);
			}else {
				currentNode.setLeft(rotateLeft(currentNode.getLeft()));
				currentNode = rotateRight(currentNode);
			}
		}
		return currentNode;
	}
	
	private Node rotateLeft(Node y) {
		Node x = y.getRight();
		Node z = x.getLeft();
		y.setRight(z);
		x.setLeft(y);
		updateNodeHeight(y);
		updateNodeHeight(x);
		return x;
	}
	
	private Node rotateRight(Node y) {
		Node x = y.getLeft();
		Node z = x.getRight();
		y.setLeft(z);
		x.setRight(y);
		updateNodeHeight(y);
		updateNodeHeight(x);
		return x;
	}

	@Override
	public String toString() {
		return "BinaryTree [root=" + root + "]";
	}
	
}
