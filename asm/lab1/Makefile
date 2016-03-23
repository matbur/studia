name := $(shell ls *.s | grep -v dbg | cut -d. -f1)

$(name): $(name).o
	ld $< -o $@

$(name).o: $(name).s
	as $< -o $@

$(name)_dbg: $(name)_dbg.s
	gcc -g $< -o $@

$(name)_dbg.s: $(name).s
	sed "s/_start/main/g" $< > $@

.PHONY: clean debug
clean:
	rm -f $(name) *.o *dbg*

debug: $(name)_dbg
