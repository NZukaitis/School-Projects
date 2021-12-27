#pragma once
#include <cstdio>
#include <string>
class Person {
public:
	Person(std::string n) {
		name = n;
		birthday = -1;
	}

	Person(std::string n, int bDay) {
		name = n;
		birthday = bDay;
	}

	void setName(std::string n) {
		name = n;
	}
	std::string getName() {
		return name;
	}

	void setBirthday(int bDay) {
		birthday = bDay;
	}
	int getBirthday() {
		return birthday;
	}
private:
	std::string name;
	//bday format YearMonthDay (Ex, 19640527)
	int birthday;
};