# M.H.R.D.

[MHRD](https://store.steampowered.com/app/576030/MHRD/) is by most standards a bad "game". It tasks you with building a CPU similar to the one in Nisan & Schocken's "[The Elements of Computing Systems](https://www.amazon.com/Elements-Computing-Systems-Building-Principles/dp/0262640686)" using NAND logic gates.

I had a lot of fun with it, however. The components I made are stored in ".wire" files, while the ones the game extrapolated are in ".unwired" files. My solution for some of the components was _not_ optimal, since I used the trick of bruteforcing any logic table with and, or, and not, but who cares :)

The game provides no way of exporting your solutions, so I wrote one that uses the game's own code to export it. It's provided here as `ExtractDesigns.java`.