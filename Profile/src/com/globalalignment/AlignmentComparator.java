/*
 * this class responsable for comparing the sequences and get the best local alignment and best score
 */
package com.globalalignment;

import com.model.scorematrix.Cell;
import com.model.scorematrix.ScoreMatrixReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author Yehia
 */
public class AlignmentComparator {

    public int kol;
    private Map<Integer, Character> qMap = new HashMap<Integer, Character>(); //map for Q sequences
    private Map<Integer, Character> dMap = new HashMap<Integer, Character>();//map for D sequences

    public ResultBean getResults(ExperimentBean exp)//this function to extract the results in result bean to view it in the application view
    {

        char[] seq_1_raw_j = stringToArr(exp.getSequenceD()); //convert input string into array
        char[] seq_2_col_i = stringToArr(exp.getSequenceQ());//convert input string into array
        dMap = fillSeqMap(seq_1_raw_j);//fill maps for D sequences
        qMap = fillSeqMap(seq_2_col_i);//fill maps for Q sequences
        ResultBean rb = new ResultBean();
        Map<String, Cell> scoreTable = initCells(seq_1_raw_j, seq_2_col_i, exp.getGOpen());//init the score table





        ArrayList<Object> objectList = this.findGlobalAlignments(seq_2_col_i, seq_1_raw_j, scoreTable, exp);//calling for finding local alignment method and get List of objects that represent the results
        double[][] matrix = (double[][]) objectList.get(0);//the final matrix
        scoreTable = (Map<String, Cell>) objectList.get(1); // this where we store the cells of the table
        Cell cornerCell = (Cell) objectList.get(2);
        rb.setCornerCell(cornerCell);

        AlignmentBean bestAlgnment = getBestGlobalAlignment(cornerCell, qMap, dMap);//get the best local alignment in double list one for q and 2 for d
        rb.setBestAlignment(bestAlgnment);

        rb.setMatrix(matrix);//set the matrix score to result bean
        return rb;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    private double getAliScore(char a, char b, String matrixScorePath)//function to get the alignment score between a and b
    {
        double score = 0;
        ScoreMatrixReader smr = new ScoreMatrixReader();//score matrix class to get data from the file
        try {
            score = smr.getScore(a, b, matrixScorePath);//mapping to matrix file to get the alignment score


        } catch (Exception e) {
            e.printStackTrace();
        }

        return score;
    }

    private static char[] stringToArr(String str)//convert string input into char array
    {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < str.length(); x++)//remove any white gaps
        {
            char c = str.charAt(x);
            if (c != ' ') {
                sb.append(c);
            }
        }
        str = sb.toString();
        str = str.toUpperCase().trim(); //method to makes the chars in a Uniformity
        char[] strArr = new char[str.length()];
        for (int x = 0; x < str.length(); x++) {
            strArr[x] = str.charAt(x);

        }
        return strArr;
    }

    private Map<Integer, Character> fillSeqMap(char[] charArr) //method to fill the map sequences
    {
        Map<Integer, Character> seqMap = new HashMap<Integer, Character>();//map for  sequences
        for (int x = 0; x < charArr.length; x++) {
            seqMap.put(x + 1, charArr[x]);
        }
        return seqMap;
    }

    private ArrayList<Object> findGlobalAlignments(char[] seq_2_col_i, char[] seq_1_raw_j, Map<String, Cell> scoreTable, ExperimentBean exp)//method that find the local alignment (most important method)
    {
        //Cell highScoreCell = null;

        double[][] matrix = new double[seq_2_col_i.length][seq_1_raw_j.length];
        // Map<String,Cell> highScoreCellsList = new HashMap<String,Cell>();
        for (int i = 1; i <= seq_2_col_i.length; i++) {
            //find the best local alignments
            double[] rawArr = new double[seq_1_raw_j.length];
            for (int j = 1; j <= seq_1_raw_j.length; j++) {
                Cell cell = new Cell();
                cell.setCol(i);
                cell.setRow(j);
                double upCellScore = scoreFromUpNeighbourCell(scoreTable.get("" + String.valueOf(i - 1) + "," + String.valueOf(j)), exp.getGOpen());//get score from upCell


                double sideCellScore = scoreFromSideNeighbourCell(scoreTable.get("" + String.valueOf(i) + "," + String.valueOf(j - 1)), exp.getGOpen());//get score from side Cell

                double aliScore = getAliScore(seq_1_raw_j[j - 1], seq_2_col_i[i - 1], exp.getScoreMatrixPath());//get score from alinment of a and b characters
                //get score from alinment of a and b characters
                double mCellScore = scoreTable.get("" + (i - 1) + "," + (j - 1)).getScore() + aliScore;//score from i-1,j-1 cell

                double s = 0;
                if (upCellScore >= sideCellScore)//choose higher score
                {
                    s = upCellScore;
                } else {
                    s = sideCellScore;
                }

                if (s < mCellScore)//choose higher score (do nothing)
                {
                    s = mCellScore;
                }

                double score = s;//choose higher score or 0

                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
		otherSymbols.setGroupingSeparator('.'); 
		 DecimalFormat decim=  new DecimalFormat("#.##",otherSymbols);                
                score = Double.parseDouble(decim.format(score));
                cell.setScore(score);
                rawArr[j - 1] = score;
                if (s == sideCellScore) {
                    cell.setPrevCell(scoreTable.get("" + i + "," + (j - 1)));
                    cell.setCellType('s');
                } else if (s == upCellScore) {
                    cell.setPrevCell(scoreTable.get("" + (i - 1) + "," + j));
                    cell.setCellType('d');
                } else {
                    cell.setPrevCell(scoreTable.get("" + (i - 1) + "," + (j - 1)));
                    cell.setCellType('m');
                }



                scoreTable.put("" + String.valueOf(i) + "," + String.valueOf(j), cell);
            }//end loop
            matrix[i - 1] = rawArr;
        }//end outer loop
        Cell cornerCell = scoreTable.get("" + String.valueOf(seq_2_col_i.length) + "," + String.valueOf(seq_1_raw_j.length));

        ArrayList<Object> objectList = new ArrayList<Object>();
        objectList.add(matrix);
        objectList.add(scoreTable);
        objectList.add(cornerCell);


        return objectList;
    }

    private double scoreFromUpNeighbourCell(Cell cell, double gOpen)//method to get score from up  cell
    {
        double score = 0;
        score = cell.getScore() - gOpen; //if the up cell not coming from cell on i-1,j so it is open gap
        return score;
    }

    private double scoreFromSideNeighbourCell(Cell cell, double gOpen)//method to get score from side  cell
    {

        double score = 0;
        score = cell.getScore() - gOpen;//if the up cell not coming from cell on i,j-1 so it is open gap

        return score;
    }

    private Map<String, Cell> initCells(char[] seq_raw, char[] seq_col, double gOpen)//initialize the score table
    {
        Map<String, Cell> scoreTable = new HashMap<String, Cell>();
        for (int i = 0, j = 0; i <= seq_col.length; i++)//loop to init all the i..n where j = 0 (it will be zero in local alignment condition)
        {
            Cell cell = new Cell();
            cell.setRow(j);
            cell.setCol(i);
            if (i == 0 && j == 0) {
                cell.setScore(0);
                cell.setPrevCell(null);
            } else {
                Cell preCell = scoreTable.get("" + String.valueOf(i - 1) + "," + String.valueOf(j));
                double s = preCell.getScore() + (-1 * (gOpen));
                cell.setScore(s);
                cell.setPrevCell(preCell);

            }
            scoreTable.put("" + String.valueOf(i) + "," + String.valueOf(j), cell);//using the value of i,j as key for  the score table
        }
        for (int i = 0, j = 1; j <= seq_raw.length; j++)//loop to init all the j...m where i = 0   (it will be zero in local alignment condition)
        {
            Cell cell = new Cell();
            cell.setRow(j);
            cell.setCol(i);

            Cell preCell = scoreTable.get("" + String.valueOf(i) + "," + String.valueOf(j - 1));


            double s = preCell.getScore() + (-1.0 * (gOpen));
            //s = 0;
            cell.setScore(s);
            cell.setPrevCell(preCell);
            scoreTable.put("" + String.valueOf(i) + "," + String.valueOf(j), cell);
        }
        return scoreTable;
    }

    private AlignmentBean getBestGlobalAlignment(Cell cornerCell, Map<Integer, Character> qMap, Map<Integer, Character> dMap)//method to get the best alignment by traking back the high score cells
    {

        AlignmentBean ab = new AlignmentBean();
        ab.setScore(cornerCell.getScore());
        ArrayList<Character> q = new ArrayList<Character>();
        ArrayList<Character> d = new ArrayList<Character>();
        Map<Integer, Cell> cellMap = new HashMap<Integer, Cell>();
        Integer z = 0;
        handelCells(cornerCell, cellMap, z);
        z = 0;
        this.getAli(cellMap, qMap, dMap, ab, q, d);

        String qStr = q.toString();
        String dStr = d.toString();
        ab.setD(dStr);
        ab.setQ(qStr);

       
        return ab;

    }
    Map<Integer, Character> qm = new HashMap<Integer, Character>();

    private void getAli(Map<Integer, Cell> cellMap, Map<Integer, Character> qMap, Map<Integer, Character> dMap, AlignmentBean ab, ArrayList<Character> q, ArrayList<Character> d) {


        Character dc = null;
        Character qc = null;
        for (int x = (cellMap.size() - 1); x >= 0; x--) {

            Cell c = cellMap.get(x);
            if (c.getCellType() == null) {
                continue;

            } else {
                if (c.getCellType() == 'u') {
                    dc = '-';
                    qc = qMap.get(c.getCol());

                } else if (c.getCellType() == 's') {
                    qc = '-';
                    dc = dMap.get(c.getRow());
                } else if (c.getCellType() == 'm') {
                    dc = dMap.get(c.getRow());
                    qc = qMap.get(c.getCol());
                }

                if (qc == null || dc == null); else {
                    d.add(dc);
                    q.add(qc);
                }


            }



        }


    }

    private void handelCells(Cell cc, Map<Integer, Cell> cellMap, int z) {

        if (cc == null) {
            return;
        }
        cellMap.put(z, cc);
        z++;
        handelCells(cc.getPrevCell(), cellMap, z);




    }
}
