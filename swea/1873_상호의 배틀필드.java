import java.io.*;
import java.util.*;

public class Solution {
  
  static final char GROUND = '.';
  static final char WALL_SOFT = '*';
  static final char WALL_HARD = '#';
  static final char WATER = '-';
  static final char TANK_UP = '^';
  static final char TANK_DOWN = 'v';
  static final char TANK_LEFT = '<';
  static final char TANK_RIGHT = '>';
  
  static int H;
  static int W;
  static char[][] map;
  
  static int cx;
  static int cy;
  
  static void printMap() {
    StringBuilder sb = new StringBuilder();
    for(char[] line:map) {
      for(char c:line) {
        sb.append(c);
      }
      sb.append('\n');
    }
    System.out.println(sb.substring(0, sb.length()-1));
  }
  
  static void shoot(int x, int y) {
    int nx=cx+x;
    int ny=cy+y;
    
    while(inRange(nx, ny) && !isWall(map[ny][nx])) {
      nx += x;
      ny += y;
    }
    if(inRange(nx, ny) && map[ny][nx]==WALL_SOFT) {
      map[ny][nx] = GROUND;
    }
  }
 
  static void move(int x, int y, char c) {
    int nx=cx+x;
    int ny=cy+y;
    if(inRange(nx, ny) && map[ny][nx]==GROUND) {
      map[cy][cx] = GROUND;
      cx = nx;
      cy = ny;
    }
    map[cy][cx] = c;
  };
  
  static void game(char c) {
    switch (c) {
    case 'U': move(0, -1, TANK_UP); break;
    case 'D': move(0, 1, TANK_DOWN); break;
    case 'L': move(-1, 0, TANK_LEFT); break;
    case 'R': move(1, 0, TANK_RIGHT); break;
    case 'S': {
      switch (map[cy][cx]) {
      case TANK_UP: shoot(0, -1); break;
      case TANK_DOWN: shoot(0, 1); break;
      case TANK_LEFT: shoot(-1, 0); break;
      case TANK_RIGHT: shoot(1, 0); break;
      }
    }
    }
  }
  
  static boolean inRange(int nx, int ny) {
    return 0<=nx&&nx<W && 0<=ny&&ny<H;
  }
  
  static boolean isWall(char c) {
    return c==WALL_SOFT || c==WALL_HARD;
  }
  
  static boolean isTank(char c) {
    return c==TANK_DOWN || c==TANK_UP || c==TANK_LEFT || c==TANK_RIGHT; 
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {
      
      st = new StringTokenizer(br.readLine());
      H = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      map = new char[H][W];
      
      for(int h=0; h<H; h++) {
        String line = br.readLine();
        for(int w=0; w<W; w++) {
          map[h][w] = line.charAt(w);
          if(isTank(map[h][w])) {
            cx = w; 
            cy = h;
          }
        }
      }
      
      int N = Integer.parseInt(br.readLine());
      for(char c:br.readLine().toCharArray()){
        game(c);
      }
      
      System.out.printf("#%d ", t);
      printMap();
    }
  }
}
