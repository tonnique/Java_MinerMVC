# MinerMVC_Java
Miner game witten on Java using MVC pattern

This copy of master branch before i made these changes in the program:

<ol>
  <li>Added GameState enum for game status and I replaced methods isWin(), isGameOver() at IMinerModel interface with method IGameSettings.GameState checkGameStatus(). Due these changes i made according changes at MinerGameLogic class. The isWin() method at MinerGameLogic class is still exists but now has private access modifier;</li>
  <li>I decided to avoid recursion logic at MinerGameLogic.openCell().</li>
</ol>
