package com.venkat.listOflist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * Given a binary search tree
 * create a list of all Treenodes that are in the same level
 * Either BST or DST can be used.
 * This example uses a modified BST 
 */

public class ListOfList {
	/* 
	 * helper inner class
	 */
	private class TreeNode implements Comparable<TreeNode>{
		Long value;
		TreeNode left;
		TreeNode right;
		
		TreeNode(Long value, TreeNode left, TreeNode right){
			this.value = value;

			this.left = left;
			this.right = right;
		}	
		public int compareTo(TreeNode other){
			return ( (this.value).compareTo(other.value) );
		}

	}
	// this is the main data backing store
	private TreeNode rootNode ;
	
	// used to create a balanced BST - with the sorted data
	private TreeNode createBalancedBST(long tnarray[]) {
		return addToTree(tnarray, 0, tnarray.length - 1);
	}
	private TreeNode addToTree(long arr[], int start, int end){
		if (end < start) {
			return null;
		}
		int mid = start + (end - start)/ 2;
		TreeNode n = new TreeNode(arr[mid],  null, null);
		n.left = addToTree(arr, start, mid - 1);
		n.right = addToTree(arr, mid + 1, end);
		return n;
	}
	private List<List<TreeNode>> createLevelLinkedList(TreeNode curr_tn){
		List<List<TreeNode>> lists = new ArrayList<List<TreeNode>>();
		
		// create the first level one - which is the root itself.
		LinkedList<TreeNode> current = new LinkedList<TreeNode>() ;
		if (curr_tn != null)
			current.add(curr_tn);
		
		while (current.size() > 0){
			lists.add( current);
			
			LinkedList<TreeNode> parent_list = current;
			current = new  LinkedList<TreeNode>();
			for(TreeNode node : parent_list){
				if (node.left != null)
					current.add(node.left);
				
				if (node.right != null)
					current.add(node.right);
			}
		}
		
		return (lists);
	}
	/*
	 * modified print 
	 * to visualize result
	 */
	private void printList(List<TreeNode> tlist){
		System.out.print("Printing list...");
		for(TreeNode node : tlist){
			System.out.print("," + node.value) ;
		}
		System.out.println("$");
			
	}
	/*
	 * another helper print class
	 * prints like a level with indentation
	 * proportional - inv prop - to level
	 */
	private void printLL(List<List<TreeNode>> llt){
		int num = llt.size();
		
		for( List<TreeNode> lst : llt){
			for(int i = 0 ; i < num ; i++){
				System.out.print("   ");
			}
			for(TreeNode node : lst){
				System.out.print("," + node.value) ;
			}
			num--;
			System.out.println(" ");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long[] ldata = {10L,12L,17L,21L,2L,15L,16L,24L,28L,50L,40L,31L,31L,58L,59L};

		Arrays.sort(ldata);
		
		ListOfList lol = new ListOfList();
		
		lol.rootNode = lol.createBalancedBST(ldata);
		
		List<List<TreeNode>> result = lol.createLevelLinkedList(lol.rootNode) ;
		
		for( List<TreeNode> lst : result)
			lol.printList( lst );
		
		lol.printLL(result);
	}

}
