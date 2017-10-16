# MinerMVC_Java
Miner game witten on Java using MVC pattern

This copy of master branch <strong><em>before i made</em></strong> these <strong><em>changes</em></strong> in the program:

<ol>
  <li>Added GameState enum for game status and I replaced methods isWin(), isGameOver() at IMinerModel interface with method IGameSettings.GameState checkGameStatus(). Due these changes i made according changes at MinerGameLogic class. The isWin() method at MinerGameLogic class is still exists but now has private access modifier.</li>
  <li>I decided to avoid recursion logic at MinerGameLogic.openCell() method because of risk to have the StackOverflow Exception. Though, my program does some checks of game initial values, like height and width of minefield. And thus my program allows to set maximum values limited by certain values, and they should not to lead to exception. Nevertheless i did removed recursion logic in case of some programmer who wants to use my classes has a huge monitor, and he or she has changed maximim values at IGameSettings interface to larger. At such hypotetic situation recurcive logic can crash the program with stack overflow exception.</li>
</ol>
