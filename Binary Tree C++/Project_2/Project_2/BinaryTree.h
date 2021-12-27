/**
* Binary tree that holds nodes of type <T>
* @author Nicholas Zukaitis
**/
#pragma once
#include <cstdio>
#include <list>
#include "Node.h"
#include "Person.h"
template <class T>
class BinaryTree {
public:
	BinaryTree() {
		root = NULL;
	}
	BinaryTree(T element) {
		root = new Node<T>(element);
	}

	/**
	* Adds an element of type <T> to the binary tree.
	* @param element The element added to the binary tree.
	**/
	void add(T* element) {
		if (root == NULL) {
			root = new Node<T>(element);
			return;
		}
		if (element == NULL) {
			return;
		}
		Node<T>* temp = root;
		while (temp->getLeftChild() != NULL || temp->getRightChild() != NULL) {
			if (compare(temp->getKey(), element) > 0) {
				if (temp->getLeftChild() == NULL) {
					temp->setLeftChild(new Node<T>(element));
					return;
				}
				else temp = temp->getLeftChild();
			}
			else {
				if (temp->getRightChild() == NULL) {
					temp->setRightChild(new Node<T>(element));
					return;
				}
				else temp = temp->getRightChild();
			}
		}
		if (compare(temp->getKey(), element) > 0) {
			temp->setLeftChild(new Node<T>(element));
			return;
		}
		else {
			temp->setRightChild(new Node<T>(element));
		}
	}

	/**
	* Locates and returns a node that contains the <T> with a specific key.
	* @param name The key used to identify an instance of type <T>.
	* @return Returns a node that contains the <T> with a specific key, or {@code NULL} if node is not in the binary tree.
	**/
	Node<T>* get(std::string name) {
		if (root == NULL) {
			return NULL;
		}
		if (name.c_str() == NULL) {
			return NULL;
		}
		Node<T>* temp = root;
		Node<T>* element = new Node<T>(new Person(name));

		while (temp != NULL){
			int comparison = compare(temp->getKey(), element->getKey());
			if (comparison == 0) {
				return temp;
			}
			else if (comparison < 0) {
				temp = temp->getRightChild();
				continue;
			}
			else {
				temp = temp->getLeftChild();
				continue;
			}
		}
		return NULL;
	}

	/**
	* Removes and returns a specific node from the binary tree.
	* @param name The key used to identify an instance of type <T>
	* @return Returns the node that was removed, or {@code NULL} if the node was not in the binary tree.
	**/
	Node<T>* remove(std::string name) {
		Node<T>* temp = root;
		Node<T>* element = get(name);

		if (element == NULL) {
			return NULL;
		}
		else {
			if (temp->isLeafNode()) {
				root = NULL;
				return temp;
			}
			while (!temp->isLeafNode()) {
				if (compare(temp->getKey(), element->getKey()) > 0) {
					if (compare(temp->getLeftChild()->getKey(), element->getKey()) == 0) {
						if (temp->getLeftChild()->isLeafNode()) {
							temp->setLeftChild(NULL);
							return element;
						}
						else {
							if (temp->getLeftChild()->getLeftChild() != NULL) {
								Node<T>* subTree = temp->getLeftChild()->getLeftChild();
								temp->setLeftChild(NULL);
								
								addSubtree(subTree);
								return element;
							}
							if (temp->getLeftChild()->getRightChild() != NULL) {
								Node<T>* subTree = temp->getLeftChild()->getRightChild();
								temp->setLeftChild(NULL);

								addSubtree(subTree);
								return element;
							}
						}
					}
					temp = temp->getLeftChild();
				}
				else {
					if (compare(temp->getRightChild()->getKey(), element->getKey()) == 0) {
						if (temp->getRightChild()->isLeafNode()) {
							temp->setRightChild(NULL);
							return element;
						}
						else {
							if (temp->getRightChild()->getLeftChild() != NULL) {
								Node<T>* subTree = temp->getRightChild()->getLeftChild();
								temp->setRightChild(NULL);

								addSubtree(subTree);
								return element;
							}
							if (temp->getRightChild()->getRightChild() != NULL) {
								Node<T>* subTree = temp->getRightChild()->getRightChild();
								temp->setRightChild(NULL);

								addSubtree(subTree);
								return element;
							}
						}
					}
					temp = temp->getRightChild();
				}
			}
		}
		return NULL;
	}

	/**
	* Generates and returns an iterable list of all the nodes in the binary tree.
	* @return Returns the list of nodes in the binary tree.
	**/
	std::list<T>* iterator() {
		std::list<T> *elements = new std::list<T>;
		return traverse(root, elements);
	}

private:
	/**
	* Node<T> that represents the root of the binary tree.
	**/
	Node<T> *root;

	/**
	* Compares two objects to determine placement in binary tree.
	* @param o1 The first object in the comparison.
	* @param o2 The second object in the comparison.
	* @return Returns >0 if o1>02, <0 if o1<o2 and 0 if o1==o2.
	**/
	int compare(T *o1, T *o2) {
		o1 = (Person*)o1;
		o2 = (Person*)o2;
		return o1->getName().compare(o2->getName());
	}

	/**
	* Traverses the binary tree to generate list for {@code iterator()}.
	* @param root The root of the tree being traversed.
	* @param elements The list to add items to.
	* @return Returns a list containing all of the objects in the binary tree.
	**/
	std::list<T> *traverse(Node<T>* root, std::list<T>* elements) {
		if (root == NULL) {
			return NULL;
		}

		elements->push_back(*root->getKey());
		traverse(root->getLeftChild(), elements);
		traverse(root->getRightChild(), elements);

		return elements;
	}

	/**
	* Adds a subtree of nodes to the binary tree.
	* @param root The root of the subtree.
	**/
	void addSubtree(Node<T>* root) {
		if (root == NULL) {
			return;
		}
		add(root->getKey());
		addSubtree(root->getLeftChild());
		addSubtree(root->getRightChild());

		return;
	}


};