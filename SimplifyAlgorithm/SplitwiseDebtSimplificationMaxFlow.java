package Problems.SplitWise.SimplifyAlgorithm;

import java.util.*;

public class SplitwiseDebtSimplificationMaxFlow {

    public static List<Debt> simplifyDebts(List<Debt> debts) {
        Map<String, Double> netBalances = new HashMap<>();

        // Calculate net balances for each person
        for (Debt debt : debts) {
            netBalances.put(debt.getDebtor(), netBalances.getOrDefault(debt.getDebtor(), 0.0) - debt.getAmount());
            netBalances.put(debt.getCreditor(), netBalances.getOrDefault(debt.getCreditor(), 0.0) + debt.getAmount());
        }

        // Create lists of debtors and creditors
        List<String> debtors = new ArrayList<>();
        List<String> creditors = new ArrayList<>();
        for (String person : netBalances.keySet()) {
            double balance = netBalances.get(person);
            if (balance < 0) {
                debtors.add(person);
            } else if (balance > 0) {
                creditors.add(person);
            }
        }

        // Construct the flow network
        String source = "source";
        String sink = "sink";
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (String debtor : debtors) {
            graph.put(debtor, new HashMap<>());
        }
        for (String creditor : creditors) {
            graph.put(creditor, new HashMap<>());
        }
        graph.put(source, new HashMap<>());
        graph.put(sink, new HashMap<>());

        // Add edges from source to debtors
        for (String debtor : debtors) {
            graph.get(source).put(debtor, -netBalances.get(debtor));
        }

        // Add edges from creditors to sink
        for (String creditor : creditors) {
            graph.get(creditor).put(sink, netBalances.get(creditor));
        }

        // Add edges between debtors and creditors with infinite capacity
        for (String debtor : debtors) {
            for (String creditor : creditors) {
                graph.get(debtor).put(creditor, Double.POSITIVE_INFINITY);
            }
        }

        // Apply Ford-Fulkerson algorithm to find max flow
        Map<String, Map<String, Double>> residualGraph = new HashMap<>();
        for (String u : graph.keySet()) {
            residualGraph.put(u, new HashMap<>(graph.get(u)));
        }

        double maxFlow = 0;
        while (true) {
            List<String> path = bfs(residualGraph, source, sink);
            if (path == null) break;

            double pathFlow = Double.POSITIVE_INFINITY;
            for (int i = 0; i < path.size() - 1; i++) {
                String u = path.get(i);
                String v = path.get(i + 1);
                pathFlow = Math.min(pathFlow, residualGraph.get(u).get(v));
            }

            for (int i = 0; i < path.size() - 1; i++) {
                String u = path.get(i);
                String v = path.get(i + 1);
                residualGraph.get(u).put(v, residualGraph.get(u).get(v) - pathFlow);
                // backward edges are added because in future we might come in state where we want to reduce the flow to the edge
                residualGraph.get(v).put(u, residualGraph.get(v).getOrDefault(u, 0.0) + pathFlow);
            }

            maxFlow += pathFlow;
        }

        // Extract simplified debts from the residual graph
        List<Debt> simplifiedDebts = new ArrayList<>();
        for (String debtor : debtors) {
            for (String creditor : creditors) {
                double flow = residualGraph.getOrDefault(creditor, new HashMap<>()).getOrDefault(debtor, 0.0);
//                simplifiedDebts.add(new Debt(debtor, creditor, flow));
                if (flow > 0) {
                    simplifiedDebts.add(new Debt(debtor, creditor, flow));
                }
            }
        }

        return simplifiedDebts;
    }

    // Breadth-first search to find path from source to sink
    private static List<String> bfs(Map<String, Map<String, Double>> graph, String source, String sink) {
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);
        parentMap.put(source, null);

        while (!queue.isEmpty()) {
            String u = queue.poll();

            for (String v : graph.get(u).keySet()) {
                if (!visited.contains(v) && graph.get(u).get(v) > 0) {
                    queue.offer(v);
                    parentMap.put(v, u);
                    visited.add(v);

                    if (v.equals(sink)) {
                        List<String> path = new ArrayList<>();
                        String current = sink;
                        while (current != null) {
                            path.add(current);
                            current = parentMap.get(current);
                        }
                        Collections.reverse(path);
                        return path;
                    }
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        // Example usage
        List<Debt> debts = new ArrayList<>();
//        debts.add(new Debt("A", "B", 100));
//        debts.add(new Debt("B", "C", 50));
//         debts.add(new Debt("C", "A", 20));
//         debts.add(new Debt("A", "C", 70));
//         debts.add(new Debt("B", "A", 40));
//         debts.add(new Debt("C", "B", 30));
        debts.add(new Debt("A", "A", 5));
        debts.add(new Debt("A", "B", 5));
        debts.add(new Debt("B", "C", 5));
        debts.add(new Debt("C", "A", 10));
        debts.add(new Debt("A", "C", 1));

        List<Debt> simplifiedDebts = simplifyDebts(debts);

        System.out.println("Simplified debts:");
        for (Debt debt : simplifiedDebts) {
            System.out.println(debt.getDebtor() + " owes " + debt.getCreditor() + " " + debt.getAmount() + " Rupees");
        }
    }
}

