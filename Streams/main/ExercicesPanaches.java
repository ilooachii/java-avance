
package main;

import domaine.Trader;
import domaine.Transaction;

import static java.util.stream.Collectors.groupingBy;

import java.util.*;
import java.util.stream.Collectors;

public class ExercicesPanaches {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));

        ExercicesPanaches main = new ExercicesPanaches(transactions);
        main.run();
    }

    private List<Transaction> transactions;

    public ExercicesPanaches(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void run() {
        // Complete the methods below based on the exercise descriptions
        exercice1();
        exercice2();
        exercice3();
        exercice4();
        exercice5();
        exercice6();
    }

    private void exercice1() {
        // TODO: Filter transactions of Cambridge, map to their values, and find max.
        System.out.println("ex6");
        var t = transactions
                .stream()
                .filter(trans -> trans.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .orElse(-1);
        System.out.println(t);
    }

    private void exercice2() {
        // TODO: Filter transactions for traders in Cambridge, group by trader, and
        // count their transactions.
        System.out.println("ex6");
        var t = transactions
                .stream()
                .filter(tr -> tr.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.groupingBy(Transaction::getTrader, Collectors.counting()));
        System.out.println(t);
    }

    private void exercice3() {
        // TODO: Filter transactions over 500, map trader names, sort by name length,
        // find the longest.
        System.out.println("ex6");
        var t = transactions
                .stream()
                .filter(trans -> trans.getValue() >= 500)
                .map(tr -> tr.getTrader().getName())
                .reduce((a, b) -> a.length() >= b.length() ? a : b)
                .orElse("Aucun trader trouvé");
        System.out.println(t);

    }

    private void exercice4() {
        // TODO: Group transactions by city, map to transaction values, and compute the
        // average.
        System.out.println("ex6");
        var t = transactions
                .stream()
                .collect(groupingBy(tr->tr.getTrader().getCity(), Collectors.averagingInt(Transaction::getValue)));

        System.out.println(t);
    }

    private void exercice5() {
        // TODO: Filter transactions in Milan, map to values, find the min, and handle
        // empty results.
        System.out.println("ex6");
        var t = transactions
                .stream()
                .filter(tr->tr.getTrader().getCity().equals("Milan"))
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .orElse(-1);
        System.out.println(t);
    }

    private void exercice6() {
        // TODO: group transaction by year
        System.out.println("ex6");
        var t = transactions
                .stream()
                .collect(groupingBy(Transaction::getYear, Collectors.toList()));
        System.out.println(t);
    }
}
