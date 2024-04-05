import random
import math

def rockpaperscissor(i):
    dict = {0: 'rock', 1: 'paper', 2: 'scissor'}
    n = math.ceil(random.random()*2)
    
    user = dict[i]
    machine = dict[n]
    
    print(user, " vs ", machine)
    
    if i == n:
        print('tie 😼')
    elif i == 1 and n == 0 or i == 2 and n == 1 or i == 0 and n == 2:
        print('You won :>')
    else:
        print('womp womp')
    
    
def main():
    print("Last minute commit so here is rock paper scissor LOL")
    print("Enter 0 for rock, 1 for paper, 2 for scissor: ", end="")
    i = int(input())
    while i < 0 or i > 2:
        print("Enter 0 for rock, 1 for paper, 2 for scissor: ", end="")
        i = int(input())
    rockpaperscissor(i)
    
    
if __name__ == '__main__':
    main()
        