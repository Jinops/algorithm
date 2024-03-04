// WIP

import java.io.*;
import java.util.*;

public class Main {
  static char[][] faceU;
  static char[][] faceD;
  static char[][] faceF;
  static char[][] faceB;
  static char[][] faceL;
  static char[][] faceR;

  static HashMap<Character, char[][]> faceSet;
  static HashMap<Character, char[][][]> linkedFaceSet;
  static HashMap<Character, int[][]> linkedIdxSet; // x,y
  
  static void initSet() {
    linkedFaceSet.put('U', new char[][][]{faceB, faceR, faceF, faceL});
    linkedFaceSet.put('D', new char[][][]{faceF, faceR, faceB, faceL});
    linkedFaceSet.put('F', new char[][][]{faceU, faceR, faceD, faceL});
    linkedFaceSet.put('B', new char[][][]{faceU, faceL, faceD, faceR});
    linkedFaceSet.put('L', new char[][][]{faceU, faceF, faceD, faceB});
    linkedFaceSet.put('R', new char[][][]{faceU, faceB, faceD, faceF});
  }
  static void initIdxSet() {
    linkedIdxSet.put('U', new int[][]{{-1,2},{-1,2},{-1,2},{-1,2}});
    linkedIdxSet.put('D', new int[][]{{-1,0},{-1,0},{-1,0},{-1,0}});
    linkedIdxSet.put('F', new int[][]{{-1,2},{0,-1},{-1,0},{2,-1}});
    linkedIdxSet.put('B', new int[][]{{-1,0},{0,-1},{-1,2},{2,-1}});
    linkedIdxSet.put('L', new int[][]{{0,-1},{0,-1},{0,-1},{0,-1}});
    linkedIdxSet.put('R', new int[][]{{2,-1},{2,-1},{2,-1},{2,-1}});
  }
  
  static void initFaces() {
    faceSet.put('U', faceU);
    faceSet.put('D', faceD);
    faceSet.put('F', faceF);
    faceSet.put('B', faceB);
    faceSet.put('L', faceL);
    faceSet.put('R', faceR);
    
    for(int i=0; i<3; i++) {
      for(int j=0; j<3; j++) {
        faceU[i][j] = 'w';
        faceD[i][j] = 'y';
        faceF[i][j] = 'r';
        faceB[i][j] = 'o';
        faceL[i][j] = 'g';
        faceR[i][j] = 'b';
      }
    }
  }
  
  static int getIdx(int i) {
    if(i<0) {
      return 4+i;
    }
    return i%4;
  }
  
  
  static void rotate(char curFace, char dir) {
    char[][] face = faceSet.get(curFace);
    char[][][] linkedFaces = linkedFaceSet.get(curFace);
    int[][] linkedIdxs = linkedIdxSet.get(curFace);

    if(dir=='+') {
      for(int i=0; i<4; i++) {
      }
    } else {
      for(int i=3; i>=0; i--) {
        
      }
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    initIdxSet();
    
    int T = Integer.parseInt(br.readLine());
    for(int i=0; i<T; i++) {
      
      initFaces();
      
      int N = Integer.parseInt(br.readLine());
      String[] strs = br.readLine().split(" ");
      for(String str:strs) {
        char face = str.charAt(0);
        char dir = str.charAt(1);
        rotate(face, dir);
      }
      
    }
    
  }
}
