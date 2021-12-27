#pragma once
#include <cstdio>
template <class T>
class Node {
public:
	Node() {
		//default constructor does nothing
	}
	Node(T *value) {
		key = value;
		leftChild = NULL;
		rightChild = NULL;
	}
	Node(T *value, Node *left, Node *right) {
		key = value;
		leftChild = left;
		rightChild = right;
	}
	T *getKey() {
		return key;
	}
	Node *getLeftChild() {
		return leftChild;
	}
	Node *getRightChild() {
		return rightChild;
	}
	void setLeftChild(Node *left) {
		leftChild = left;
	}
	void setRightChild(Node *right) {
		rightChild = right;
	}

	bool isEmpty() {
		if (key == NULL) return true;
		else return false;
	}

	bool isLeafNode() {
		if (leftChild == NULL && rightChild == NULL) {
			return true;
		}
		return false;
	}


private:
	Node *leftChild;
	Node *rightChild;
	T *key;
};