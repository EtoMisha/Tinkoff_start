import java.util.*;

public class Task06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int qty = scanner.nextInt();
//        int qty = 7;

        Graph graph = new Graph();
//        graph.addEdge(2, 6);
//        graph.addEdge(5, 6);
//        graph.addEdge(2, 5);
//        graph.addEdge(2, 2);
//        graph.addEdge(6, 8);
//        graph.addEdge(2, 2);
//        graph.addEdge(0, 2);
//        graph.addEdge(0, 5);

//        graph.addEdge(0, 2);
//        graph.addEdge(2, 5);
//        graph.addEdge(2, 6);
//        graph.addEdge(5, 8);
//        graph.addEdge(8, 9);
//        graph.addEdge(6, 7);
//        graph.addEdge(7, 8);

        for (int i = 0; i < qty; i++) {
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();

            graph.addEdge(num1, num2);
        }

//        graph.print();
        System.out.println(graph.getMaxDistance());

        scanner.close();
    }
}

class Graph {
    private final Map<Vertex, List<Vertex>> adj;
    private int maxDistance;
    private final Vertex start;

    static class Vertex implements Comparable{
        final int value;
        int distance;

        public Vertex(int value) {
            this.value = value;
        }
//
//        @Override
//        public String toString() {
//            return String.valueOf(value);
//        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return value == vertex.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public int compareTo(Object o) {
            Vertex other = (Vertex) o;
            return Integer.compare(this.value, other.value);
        }
    }

    Graph() {
        this.start = new Vertex(0);
        this.adj = new HashMap<>();
    }

    void addEdge(int value1, int value2) {
        Vertex vertex = new Vertex(value1);
        adj.computeIfAbsent(vertex, k -> new ArrayList<>());
        adj.get(vertex).add(new Vertex(value2));
    }

//    void print() {
//        for (Vertex vertex : adj.keySet()) {
//            System.out.print(vertex.toString() + adj.get(vertex) + "   ");
//        }
//        System.out.println();
//    }

    public int getMaxDistance() {
        searchRec(start);
        return maxDistance;
    }

    /*
     * Обходит вершины, считает дистанцию от начала до каждой и отслеживает максимальный путь который нашелся.
     * В начале сортирует соседей вершины, чтобы петли обработались в первую очередь. Не самое изящное решение.
     * Еще очень не круто, что если нашелся более длинный путь, повторно обходит всё в глубину.
     * Не успеваю переделать, пусть хотя бы так работает :)
     */
    void searchRec(Vertex start) {
//        System.out.println("-- start " + start);
        adj.get(start).sort(Vertex::compareTo);

        for (Vertex vertex : adj.get(start)) {
//            System.out.println("start " + start.distance + ", vertex " + vertex.distance);
            if (vertex.equals(start)) {
                start.distance++;
            }
            if (start.distance + 1 > vertex.distance) {
                vertex.distance = start.distance + 1;
                if (vertex.distance > maxDistance) {
                    maxDistance = vertex.distance;
                }
//                System.out.println(start + "->" + vertex + " " + vertex.distance);

                if (adj.get(vertex) != null && !vertex.equals(start)) {
                    searchRec(vertex);
                }
            }
        }
    }

}
