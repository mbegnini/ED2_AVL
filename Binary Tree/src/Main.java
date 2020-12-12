
public class Main {
	
	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();
		bt.insertNode(new Data(10));
		bt.insertNode(new Data(5));
		bt.insertNode(new Data(1));
		bt.insertNode(new Data(15));
		bt.insertNode(new Data(7));
		bt.insertNode(new Data(8));
		bt.insertNode(new Data(20));
		bt.insertNode(new Data(12));
		bt.insertNode(new Data(6));
		bt.insertNode(new Data(3));
		bt.insertNode(new Data(2));
		bt.insertNode(new Data(4));
		
		
		System.out.println(bt);
		bt.removeNode(10);
		System.out.println(bt);

		//bt.removeNode(15);
		//System.out.println(bt);
		//bt.preOrder();
	}
	
	
	/*
	 * fator balanceamento 2 desbalanceado para direita
	 * 		filho da direita fator de balanceamento 1 (levementa desbalanceado para direita )
	 * 			rotar o nó para esquerda
	 * 		filho da direita fator de balancemento -1 (levemente desbalanceado para esquerda )
	 * 			rotar o filho da direita para direta
	 * 			rotar o nó para esquerda
	 * 
	 * fator balanceamento -2 (desbalanceado para esquerda)
	 * 		filho da esquerda fator de balanceamento -1 (levemente desbalanceado para esquerda)
	 * 			rotar o nó para direita
	 * 		filho da esquerda fator de balanceamento +1 (levemente desbalanceado para direita)
	 * 			rotar o filho da esquerda para esquerda
	 * 			rota o nó para direita
	 */
	
	

}
