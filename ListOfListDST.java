package com.venkat.listOflist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * Given a binary search tree
 * create a list of all Treenodes that are in the same level
 * Either BST or DST can be used.
 * This example uses a modified DST
 */

public class ListOfListDST {
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
	private void createLevelLinkedListDST(TreeNode curr_tn, ArrayList<LinkedList<TreeNode>> lists, int level){
	
		// base case for recursion
		if (curr_tn == null)
			return ;
		
		// declare the List that will be used
		LinkedList<TreeNode> curr_list = null;
		
		System.out.println(  "level  " + level +  "nothing");
		System.out.println(" size is " + lists.size() + "$");
		// if lists size is the level - first time we are entering for that level.
		if (lists.size() == level){
			curr_list = new LinkedList<TreeNode>();
			lists.add(curr_list);
		} else {
			curr_list = lists.get(level);
		}
		
		// eitherway once the list to add is retrieved - add the node
		curr_list.add(curr_tn);
		createLevelLinkedListDST(curr_tn.left, lists, level+1);
		createLevelLinkedListDST(curr_tn.right, lists, level+1);
		
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
	private void printLL(ArrayList<LinkedList<TreeNode>> llt){
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
		
		ListOfListDST lol = new ListOfListDST();
		
		lol.rootNode = lol.createBalancedBST(ldata);
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
		lol.createLevelLinkedListDST(lol.rootNode, result, 0) ;
		
		for( List<TreeNode> lst : result)
			lol.printList( lst );
		
		lol.printLL(result);
	}

}
