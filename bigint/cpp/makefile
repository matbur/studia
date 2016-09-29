FLAGS = -std=c++11 -Wall -Wextra -pedantic

calc: main.cpp parser.o integer.o natural.o
	g++ $(FLAGS) $^ -o $@

%.o: %.cpp %.h
	g++ $(FLAGS) -c $< -o $@

.PHONY: clear

clear:
	rm *.o calc
