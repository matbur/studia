CXXFLAGS = -Wall
CXX = g++

objects = fraction.o section.o main.o


lab2: $(objects)
	$(CXX) $(CXXFLAGS) $^ -o $@

main.o: main.cpp
section.o: section.cpp
fraction.o: fraction.cpp

.PHONY: clean
clean:
	rm -rf *.o
