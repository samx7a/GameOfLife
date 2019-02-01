To compile:

From this directory run:

    javac -d . src/*

To run:
A user can either supply one or zero command line arguments. A filename can be supplied which will be used to populate the starting cell grid. The textfile should be formatted so that cells are line separated and x,y are comma separated.

x,y

x,y




    java Grid

-- or --

    java Grid examples/toad.txt

The examples directory contains some example starting states for the cell grid. These are taken from https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Examples_of_patterns

To quit:

    ctrl-c   
