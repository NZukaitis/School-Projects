/**
* Tests functionality of the Database, Person and Node classes.
* @author Nicholas Zukaitis
**/
#pragma once
#include <list>
#include <iostream>
#include <fstream>
#include "Database.h"
#include "Person.h"
#include "Node.h"
class Test {
public:
	/**
	* Tests functionality of the Database, Person and Node classes.
	**/
	void startTest() {
		/**
		* String that holds an individual line of a text file.
		**/
		std::string line;
		/**
		* ifstream object that represents a text file containing test data.
		**/
		std::ifstream file("test_data.txt");

		/**
		* List of strings that contains the data from {@code file}.
		**/
		std::list<std::string>* fileToString = new std::list<std::string>();

		/**
		* Instance of the Database class used to test functionality.
		**/
		Database *db = new Database();

		if (file.is_open()) {
			while (std::getline(file, line)) {
				fileToString->push_back(line);
			}
		}
		file.close();

		for (std::list<std::string>::iterator i = fileToString->begin(); i != fileToString->end(); i++) {
			int comma = i->find(",");
			db->add(new Person(i->substr(0, comma), std::stoi(i->substr(comma + 1))));
			
		}
		printAllNames(db->getIterator());

		std::cout << '\n';
		
		std::cout << db->remove("Ricky")->getKey()->getName() + '\n';

		std::cout << '\n';

		db->refreshIterator();
		printAllNames(db->getIterator());

		std::cout << '\n';

		std::cout << db->get("Terry")->getKey()->getName() + '\n';
		std::cout << db->get("yrreT") + '\n';

		std::cout << '\n';
		std::cout << '\n';

		std::cout << db->get("Musa")->getKey()->getBirthday();
		std::cout << '\n';
		db->modify("Musa", 20010527);
		std::cout << db->get("Musa")->getKey()->getBirthday();
		
		std::cout << '\n';
		std::cout << '\n';

		db->modify("Musa", "Nick");
		db->refreshIterator();
		printAllNames(db->getIterator());

		std::cout << '\n';

		std::cout << db->get("Nick")->getKey()->getBirthday();
	}

private:
	/**
	* Prints all of the names of the People objects that are currently in {@code db}.
	* @param list The list that is iterated over.
	**/
	void printAllNames(std::list<Person>* list) {
		for (std::list<Person>::iterator i = list->begin(); i != list->end(); i++) {
			std::cout << i->getName();
			std::cout << '\n';
		}
	}

	/**
	* Prints all of the birthdays of the People objects that are currently in {@code db}.
	* @param list The list that is iterated over.
	**/
	void printAllBirthdays(std::list<Person>* list) {
		for (std::list<Person>::iterator i = list->begin(); i != list->end(); i++) {
			std::cout << i->getBirthday();
			std::cout << '\n';
		}
	}
};
