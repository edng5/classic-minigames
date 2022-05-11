from Tkinter import*
top=Tk(className=' Tic-Tac-Toe GUI')
turn=0
player=1
gWonX = 0
gWonO = 0
tiecheck = 0
vX = 'X'
vO = 'O'
titleLabel=StringVar()
scoreboardLabel=StringVar()
titleLabel=Label(text=' Tic-Tac-Toe ',font='Fixedsys 65 bold',fg='Black')
titleLabel.grid(row=0,column=0,columnspan=27,sticky=N+E+S+W)
titleLabel.config(bg='Gold')

player1Label=Label(text="(X) Player 1",font='Fixedsys 17 bold')
player1Label.grid(row=31,column=0,rowspan=2,columnspan=11,sticky=N+W+S+E)
player1Label.config(bg='Light Goldenrod',fg='Black')

player2Label=Label(text="(O) Player 2",font='Fixedsys 17 bold')
player2Label.grid(row=31,column=13,rowspan=2,columnspan=11,sticky=N+W+S+E)
player2Label.config(bg='Pale Goldenrod',fg='white')

def box1No():
    global turn
    global player
    turn+=1
    if player==1:
        butn1.config(text=vX,state='disabled')
        player=2
        toggleColorX()
        checkWinX()
        tie()
    elif player==2:
        butn1.config(text=vO,state='disabled')
        player=1
        toggleColorO()
        checkWinO()
        tie()
    
def box2No():
    global turn
    global player
    turn+=1
    if player==1:
        butn2.config(text=vX,state='disabled')
        player=2
        toggleColorX()
        checkWinX()
        tie()
    elif player==2:
        butn2.config(text=vO,state='disabled')
        player=1
        toggleColorO()
        checkWinO()
        tie()
def box3No():
    global turn
    global player
    turn+=1
    if player==1:
        butn3.config(text=vX,state='disabled')
        player=2
        toggleColorX()
        checkWinX()
        tie()
    elif player==2:
        butn3.config(text=vO,state='disabled')
        player=1
        toggleColorO()
        checkWinO()
        tie()
def box4No():
    global turn
    global player
    turn+=1
    if player==1:
        butn4.config(text=vX,state='disabled')
        player=2
        toggleColorX()
        checkWinX()
        tie()
    elif player==2:
        butn4.config(text=vO,state='disabled')
        player=1
        toggleColorO()
        checkWinO()
        tie()
def box5No():
    global turn
    global player
    turn+=1
    if player==1:
        butn5.config(text=vX,state='disabled')
        player=2
        toggleColorX()
        checkWinX()
        tie()
    elif player==2:
        butn5.config(text=vO,state='disabled')
        player=1
        toggleColorO()
        checkWinO()
        tie()
def box6No():
    global turn
    global player
    turn+=1
    if player==1:
        butn6.config(text=vX,state='disabled')
        player=2
        toggleColorX()
        checkWinX()
        tie()
    elif player==2:
        butn6.config(text=vO,state='disabled')
        player=1
        toggleColorO()
        checkWinO()
        tie()
def box7No():
    global turn
    global player
    turn+=1
    if player==1:
        butn7.config(text=vX,state='disabled')
        player=2
        toggleColorX()
        checkWinX()
        tie()
    elif player==2:
        butn7.config(text=vO,state='disabled')
        player=1
        toggleColorO()
        checkWinO()
        tie()
def box8No():
    global turn
    global player
    turn+=1
    if player==1:
        butn8.config(text=vX,state='disabled')
        player=2
        toggleColorX()
        checkWinX()
        tie()
    elif player==2:
        butn8.config(text=vO,state='disabled')
        player=1
        toggleColorO()
        checkWinO()
        tie()
def box9No():
    global turn
    global player
    turn+=1
    if player==1:
        butn9.config(text=vX,state='disabled')
        player=2
        toggleColorX()
        checkWinX()
        tie()
    elif player==2:
        butn9.config(text=vO,state='disabled')
        player=1
        toggleColorO()
        checkWinO()
        tie()
def clearButtonNo():
    global turn
    global tiecheck
    tiecheck=0
    turn=0
    butn1.config(text=' ',state='active',font='Verdana 50',bg='Lemon Chiffon')
    butn2.config(text=' ',state='active',font='Verdana 50',bg='Lemon Chiffon')
    butn3.config(text=' ',state='active',font='Verdana 50',bg='Lemon Chiffon')
    butn4.config(text=' ',state='active',font='Verdana 50',bg='Lemon Chiffon')
    butn5.config(text=' ',state='active',font='Verdana 50',bg='Lemon Chiffon')
    butn6.config(text=' ',state='active',font='Verdana 50',bg='Lemon Chiffon')
    butn7.config(text=' ',state='active',font='Verdana 50',bg='Lemon Chiffon')
    butn8.config(text=' ',state='active',font='Verdana 50',bg='Lemon Chiffon')
    butn9.config(text=' ',state='active',font='Verdana 50',bg='Lemon Chiffon')
    clearButton.config(text='Clear Board')
    winnerLabel.config(text='Who will win?')

def lockButtons():
    butn1.config(state='disabled')
    butn2.config(state='disabled')
    butn3.config(state='disabled')
    butn4.config(state='disabled')
    butn5.config(state='disabled')
    butn6.config(state='disabled')
    butn7.config(state='disabled')
    butn8.config(state='disabled')
    butn9.config(state='disabled')
    playagain()

def checkWinX():
    global gWonX  
    global tiecheck
    if butn1.cget("text")==butn2.cget("text")==butn3.cget("text")==vX:
        butn1.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn2.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn3.config(text='X',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 1 Wins!',font= 'Fixedsys 23 bold')
        gWonX+=1
        addScoreX()
        tiecheck = 1
        lockButtons()
    if butn4.cget("text")==butn5.cget("text")==butn6.cget("text")==vX:
        butn4.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn5.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn6.config(text='X',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 1 Wins!',font= 'Fixedsys 23 bold')
        gWonX+=1
        addScoreX()
        tiecheck = 1
        lockButtons()
    if butn7.cget("text")==butn8.cget("text")==butn9.cget("text")==vX:
        butn7.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn8.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn9.config(text='X',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 1 Wins!',font= 'Fixedsys 23 bold')
        gWonX+=1
        addScoreX()
        tiecheck = 1
        lockButtons()
    if butn1.cget("text")==butn4.cget("text")==butn7.cget("text")==vX:
        butn1.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn4.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn7.config(text='X',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 1 Wins!',font= 'Fixedsys 23 bold')
        gWonX+=1
        addScoreX()
        tiecheck = 1
        lockButtons()
    if butn2.cget("text")==butn5.cget("text")==butn8.cget("text")==vX:
        butn2.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn5.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn8.config(text='X',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 1 Wins!',font= 'Fixedsys 23 bold')
        gWonX+=1
        addScoreX()
        tiecheck = 1
        lockButtons()
    if butn3.cget("text")==butn6.cget("text")==butn9.cget("text")==vX:
        butn3.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn6.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn9.config(text='X',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 1 Wins!',font= 'Fixedsys 23 bold')
        gWonX+=1
        addScoreX()
        tiecheck = 1
        lockButtons()
    if butn1.cget("text")==butn5.cget("text")==butn9.cget("text")==vX:
        butn1.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn5.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn9.config(text='X',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 1 Wins!',font= 'Fixedsys 23 bold')
        gWonX+=1
        addScoreX()
        tiecheck = 1
        lockButtons()
    if butn3.cget("text")==butn5.cget("text")==butn7.cget("text")==vX:
        butn3.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn5.config(text='X',font='Verdana 50 bold',bg='Orange')
        butn7.config(text='X',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 1 Wins!',font= 'Fixedsys 23 bold')
        gWonX+=1
        addScoreX()
        tiecheck = 1
        lockButtons()

def checkWinO():
    global gWonO
    global tiecheck
    if butn1.cget("text")==butn2.cget("text")==butn3.cget("text")==vO:
        butn1.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn2.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn3.config(text='O',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 2 Wins!',font= 'Fixedsys 23 bold')
        gWonO+=1
        addScoreO()
        tiecheck = 1
        lockButtons()
    if butn4.cget("text")==butn5.cget("text")==butn6.cget("text")==vO:
        butn4.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn5.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn6.config(text='O',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 2 Wins!',font= 'Fixedsys 23 bold')
        gWonO+=1
        addScoreO()
        tiecheck = 1
    if butn7.cget("text")==butn8.cget("text")==butn9.cget("text")==vO:
        butn7.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn8.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn9.config(text='O',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 2 Wins!',font= 'Fixedsys 23 bold')
        gWonO+=1
        addScoreO()
        tiecheck = 1
        lockButtons()
    if butn1.cget("text")==butn4.cget("text")==butn7.cget("text")==vO:
        butn1.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn4.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn7.config(text='O',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 2 Wins!',font= 'Fixedsys 23 bold')
        gWonO+=1
        addScoreO()
        tiecheck = 1
        lockButtons()
    if butn2.cget("text")==butn5.cget("text")==butn8.cget("text")==vO:
        butn2.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn5.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn8.config(text='O',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='**Player 2 Wins**',font= 'Fixedsys 23 bold')
        gWonO+=1
        addScoreO()
        tiecheck = 1
        lockButtons()
    if butn3.cget("text")==butn6.cget("text")==butn9.cget("text")==vO:
        butn3.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn6.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn9.config(text='O',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 2 Wins!',font= 'Fixedsys 23 bold')
        gWonO+=1
        addScoreO()
        tiecheck = 1
        lockButtons()
    if butn1.cget("text")==butn5.cget("text")==butn9.cget("text")==vO:
        butn1.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn5.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn9.config(text='O',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 2 Wins!',font= 'Fixedsys 23 bold')
        gWonO+=1
        addScoreO()
        tiecheck = 1
        lockButtons()
    if butn3.cget("text")==butn5.cget("text")==butn7.cget("text")==vO:
        butn3.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn5.config(text='O',font='Verdana 50 bold',bg='Orange')
        butn7.config(text='O',font='Verdana 50 bold',bg='Orange')
        winnerLabel.config(text='Player 2 Wins!',font= 'Fixedsys 23 bold')
        gWonO+=1
        addScoreO()
        tiecheck = 1
        lockButtons()

def tie():
    global turn   
    if turn>=9 and tiecheck == 0:
        winnerLabel.config(text='** Tie **')
        
def toggleColorX():
    player1Label.config(bg='Pale Goldenrod',fg='white')
    player2Label.config(bg='Light Goldenrod',fg='Black')
    
def toggleColorO():
    player1Label.config(bg='Light Goldenrod',fg='Black')
    player2Label.config(bg='Pale Goldenrod',fg='white')

def addScoreX():
    scoreCountX.config(text=gWonX)

def addScoreO():
    scoreCountO.config(text=gWonO)

def playagain():
    clearButton.config(text='Play Again?')

butn1=Button(text=' ',font='Verdana 50', command=box1No)
butn1.grid(row=2,column=0,columnspan=9,rowspan=9,sticky=N+W+E+S)
butn1.config(bg='Lemon Chiffon')
butn2=Button(text=' ',font='Verdana 50',command=box2No)
butn2.grid(row=2,column=9,columnspan=9,rowspan=9,sticky=N+W+E+S)
butn2.config(bg='Lemon Chiffon')
butn3=Button(text=' ',font='Verdana 50',command=box3No)
butn3.grid(row=2,column=18,columnspan=9,rowspan=9,sticky=N+W+E+S)
butn3.config(bg='Lemon Chiffon')
butn4=Button(text=' ',font='Verdana 50',command=box4No)
butn4.grid(row=11,column=0,columnspan=9,rowspan=9,sticky=N+W+E+S)
butn4.config(bg='Lemon Chiffon')
butn5=Button(text=' ',font='Verdana 50',command=box5No)
butn5.grid(row=11,column=9,columnspan=9,rowspan=9,sticky=N+W+E+S)
butn5.config(bg='Lemon Chiffon')
butn6=Button(text=' ',font='Verdana 50',command=box6No)
butn6.grid(row=11,column=18,columnspan=9,rowspan=9,sticky=N+W+E+S)
butn6.config(bg='Lemon Chiffon')
butn7=Button(text=' ',font='Verdana 50',command=box7No)
butn7.grid(row=20,column=0,columnspan=9,rowspan=9,sticky=N+W+E+S)
butn7.config(bg='Lemon Chiffon')
butn8=Button(text=' ',font='Verdana 50',command=box8No)
butn8.grid(row=20,column=9,columnspan=9,rowspan=9,sticky=N+W+E+S)
butn8.config(bg='Lemon Chiffon')
butn9=Button(text=' ',font='Verdana 50',command=box9No)
butn9.grid(row=20,column=18,columnspan=9,rowspan=9,sticky=N+W+E+S)
butn9.config(bg='Lemon Chiffon')

winnerLabel=Label(text='Who will win?',font='Fixedsys 23', fg='Black')
winnerLabel.grid(row=33,column=0,rowspan=2,columnspan=27,sticky=N+S+W+E)
winnerLabel.config(bg='Gold')

clearButton=Button(text='Clear Board',font='Fixedsys 28 bold',fg='Black',command=clearButtonNo)
clearButton.grid(row=36,column=0,rowspan=4,columnspan=27,sticky=N+E+W+S)
clearButton.config(bg='Goldenrod')

scoreCountX=Label(text=gWonX,font='Fixedsys 20',fg='Black')
scoreCountX.grid(row=31,column=11,columnspan=2,sticky=N+E+W+S)
scoreCountX.config(bg='Light Goldenrod Yellow')

scoreCountO=Label(text=gWonO,font='Fixedsys 20',fg='Black')
scoreCountO.grid(row=31,column=24,columnspan=3,sticky=N+E+W+S)
scoreCountO.config(bg='Light Goldenrod')

scoreboardLabel=Label(text='Scoreboard', font='Fixedsys 20 bold underline',fg='Black')
scoreboardLabel.grid(row=29,column=0,columnspan=27,sticky=N+W+S+E)
scoreboardLabel.config(bg='Goldenrod')

top.mainloop()
