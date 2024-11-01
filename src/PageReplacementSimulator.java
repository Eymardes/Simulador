import java.util.*;

public class PageReplacementSimulator {
    private final int[] pageSequence;
    private final int frameCount;

    public PageReplacementSimulator(int[] pageSequence, int frameCount) {
        this.pageSequence = pageSequence;
        this.frameCount = frameCount;
    }

    public int runFIFO() {
        Set<Integer> frames = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pageSequence) {
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() == frameCount) {
                    int removed = queue.poll();
                    frames.remove(removed);
                }
                frames.add(page);
                queue.add(page);
            }
        }
        return pageFaults;
    }

    public int runLRU() {
        Set<Integer> frames = new HashSet<>();
        Map<Integer, Integer> lruMap = new HashMap<>();
        int pageFaults = 0;

        for (int i = 0; i < pageSequence.length; i++) {
            int page = pageSequence[i];
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() == frameCount) {
                    int lruPage = lruMap.entrySet().stream()
                            .min(Map.Entry.comparingByValue()).get().getKey();
                    frames.remove(lruPage);
                    lruMap.remove(lruPage);
                }
                frames.add(page);
            }
            lruMap.put(page, i);
        }
        return pageFaults;
    }

    public int runClock() {
        int[] frames = new int[frameCount];
        boolean[] secondChance = new boolean[frameCount];
        int pointer = 0, pageFaults = 0;

        Arrays.fill(frames, -1);

        for (int page : pageSequence) {
            boolean found = false;
            for (int i = 0; i < frameCount; i++) {
                if (frames[i] == page) {
                    secondChance[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                pageFaults++;
                while (secondChance[pointer]) {
                    secondChance[pointer] = false;
                    pointer = (pointer + 1) % frameCount;
                }
                frames[pointer] = page;
                secondChance[pointer] = true;
                pointer = (pointer + 1) % frameCount;
            }
        }
        return pageFaults;
    }

    public int runOptimal() {
        Set<Integer> frames = new HashSet<>();
        int pageFaults = 0;

        for (int i = 0; i < pageSequence.length; i++) {
            int page = pageSequence[i];
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() == frameCount) {
                    int furthest = i, pageToRemove = -1;
                    for (int framePage : frames) {
                        int j;
                        for (j = i + 1; j < pageSequence.length; j++) {
                            if (pageSequence[j] == framePage) break;
                        }
                        if (j > furthest) {
                            furthest = j;
                            pageToRemove = framePage;
                        }
                    }
                    frames.remove(pageToRemove);
                }
                frames.add(page);
            }
        }
        return pageFaults;
    }
}

