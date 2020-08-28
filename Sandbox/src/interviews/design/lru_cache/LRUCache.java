package interviews.design.lru_cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class LRUCache {
    private int capacity;
    private Map<Integer, Integer> cache;
    private List<Integer> recencyList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<Integer, Integer>();
        this.recencyList = new LinkedList<Integer>();
    }

    public int get(int key) {
        Integer value = cache.get(key);
        // only update recency if it's in the cache already
        if (value != null) {
            updateRecency(key);
            return value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        boolean alreadyIn = cache.containsKey(key);
        if (!alreadyIn && cache.size()+1 > capacity) {
            // evict least recent
            Integer leastRecent = getLeastRecentKey();
            if (leastRecent != null) {
                cache.remove(leastRecent);
                recencyList.remove(leastRecent);
            }
        }
        cache.put(key, value);
        updateRecency(key);
    }

    private void updateRecency(Integer key) {
        int keyIndex = recencyList.indexOf(key);
        if (keyIndex != -1) {
            recencyList.remove(keyIndex);
        }
        recencyList.add(key);
    }

    private Integer getLeastRecentKey() {
        if (recencyList.size() > 0) {
            return recencyList.get(0);
        } else {
            return null;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */