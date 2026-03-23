public class tryBST {
        static class tNode {
            int key;
            tNode left;
            tNode right;

            public tNode(int key) {
                this.key = key;
                left = null;
                right= null;
            }
        }

        tNode root = null;
        public tNode insert(tNode node, int key) {
            if(node==null) {
                return new tNode(key);
            }

            if(key< node.key) {
                node.left = insert(node.left, key);
            } else {
                node.right = insert(node.right, key);
            }
            return node;

        }
        public void balancedBST(int start, int end){
            if(start > end) {
                return;
            }

            int mid = (start + end)/ 2;
            root = insert(root, mid);

            balancedBST(start, mid - 1);
            balancedBST(mid + 1, end);
        }
        public boolean isBST(tNode node, int min, int max){
            if(node == null){
                return true;
            }
            if(node.key <= min || node.key >= max){
                return false;
            }
            return isBST(node.left, min, node.key ) && isBST(node.right, node.key, max);

        }

        public tNode findMin(tNode node){
            while (node.left != null){
                node = node.left;
            }
            return node;
        }
        //This is to delete the node
        public tNode delete(tNode node, int key){
            if (node == null){
                return null;
            }

            if(key < node.key){
                node.left = delete(node.left,key);
            } else if (key > node.key){
                node.right = delete(node.right, key);
            } else {
                if(node.left == null && node.right == null){
                    return null;
                }
                if(node.left == null){
                    return node.right;
                }
                if(node.right == null){
                    return node.left;
                }

                tNode temp = findMin(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
            return node;
        }
        //Removing all even numbers
        public void removeEven(int max){
            for (int i = 2; i < max; i = i + 2){
                root = delete(root, i);
            }
        }

        public static void main(String[] args){

            int n = (int)Math.pow(2, 20) - 1;
            int reps = 30;

            long[] populateTimes = new long[reps];
            long[] removeTimes = new long[reps];

            //Populate timing
            for (int i = 0; i < reps; i++){
                tryBST t = new tryBST();

                long start = System.nanoTime();
                t.balancedBST(1, n);
                long end = System.nanoTime();

                populateTimes[i] = (end - start) / 1000000;
            }
            //Remove evens Timing
            for (int i = 0; i < reps; i++){
                tryBST t = new tryBST();
                t.balancedBST(1, n);

                long start = System.nanoTime();
                t.removeEven(n);
                long end = System.nanoTime();

                removeTimes[i] = (end - start) / 1000000;
            }
            //Average
            double sumPopulate = 0;
            double sumRemove = 0;

            for(int i = 0; i < reps; i++){
                sumPopulate += populateTimes[i];
                sumRemove += removeTimes[i];
            }
            double avgPopulate = sumPopulate / reps;
            double avgRemove = sumRemove / reps;

            // Standard Deviation
            double varPopulate = 0;
            double varRemove = 0;

            for(int i = 0; i < reps; i++){
                varPopulate += Math.pow(populateTimes[i] - avgPopulate, 2);
                varRemove += Math.pow(removeTimes[i] - avgRemove, 2);
            }

            double stdPopulate = Math.sqrt(varPopulate / reps);
            double stdRemove = Math.sqrt(varRemove / reps);
                
            System.out.println("Method\t\t n\t Avg(ms)\t StdDev");
            System.out.println("--------------------------------");
            System.out.println("Populate\t " + n + "\t " + avgPopulate + "\t " + stdPopulate);
            System.out.println("Remove\t\t " + n + "\t " + avgRemove + "\t " + stdRemove);    
        }
    }
