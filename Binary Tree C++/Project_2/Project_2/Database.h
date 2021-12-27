#pragma once
#include <cstdio>
#include <list>
#include "BinaryTree.h"
#include "Node.h"
#include "Person.h"

class Database {
public:
	Database() {
		tree = new BinaryTree<Person>();
		iterator = new std::list<Person>();
	}
	Database(BinaryTree<Person>* binaryTree) {
		tree = binaryTree;
		iterator = tree->iterator();
	}

	void add(Person* person) {
		tree->add(person);
	}
	Node<Person>* remove(std::string name) {
		return tree->remove(name);
	}
	Node<Person>* get(std::string name) {
		return tree->get(name);
	}

	void modify(std::string key, int newDate) {
		if (get(key) != NULL) {
			get(key)->getKey()->setBirthday(newDate);
		}
		
	}
	void modify(std::string key, std::string newName) {
		if (get(key) != NULL) {
			Person* prev = remove(key)->getKey();
			prev->setName(newName);
			add(prev);
		}
	}

	void refreshIterator() {
		iterator = tree->iterator();
	}

	std::list<Person>* getIterator() {
		refreshIterator();
		return iterator;
	}
private:
	BinaryTree<Person>* tree;
	std::list<Person>* iterator;
	std::list<Person*> *searchResults = new std::list<Person*>();
};
