class Solution {
    
    public List<Integer> adjList(int i, int j, int row, int col) {
        if (i == 0 && j == 0) {
            return List.of(col * (i + 1) + j, col * i + j + 1);
        }
        if (i == 0 && j == col - 1) {
            return List.of(col * (i + 1) + j, col * i + j - 1);
        }
        if (i == row - 1 && j == col - 1) {
            return List.of(col * (i - 1) + j, col * i + j - 1);
        }
        if (i == row - 1 && j == 0) {
            return List.of(col * (i - 1) + j, col * i + j + 1);
        }
        if (i == 0) {
            return List.of(col * i + j - 1, col * i + j + 1, col * (i + 1) + j);
        }
        if (j == 0) {
            return List.of(col * (i + 1) + j, col * (i - 1) + j, col * i + j + 1);
        }
        if (i == row - 1) {
            return List.of(col * (i) + j + 1, col * (i) + j - 1, col * (i - 1) + j);
        }
        if (j == col - 1) {
            return List.of(col * (i - 1) + j, col * (i + 1) + j, col * i + j - 1);
        }
        return List.of(col * i + j - 1, col * i + j + 1, col * (i + 1) + j, col * (i - 1) + j);
    }

    private void dfs(int[][] grid, Set<Integer> visited, int i, int j, int row, int col) {
        for (int el : adjList(i, j, row, col)) {
            int idx = el / col;
            int jdx = el % col;
            if (idx < 0 || idx >= row) continue;
            if (jdx < 0 || jdx >= col) continue;
            if (!visited.contains(el) && grid[idx][jdx] == 1) {
                visited.add(el);
                dfs(grid, visited, el / col, el % col, row, col);
            }
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int cnt = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited.contains(i * grid[0].length + j)) continue;
                if (grid[i][j] == 1) {
                    int before = visited.size();
                    visited.add(i * grid[0].length + j);
                    dfs(grid, visited, i, j, grid.length, grid[0].length);
                    int after = visited.size();
                    cnt = Math.max(cnt, after-before);
                }
            }
        }
        return cnt;
    }
}
