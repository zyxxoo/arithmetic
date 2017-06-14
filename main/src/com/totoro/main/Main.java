package com.totoro.main;

/**
 * Created by zhangyan on 2017/6/14.
 */
public class Main {

    public static void main(String[] argvs){

        Main test = new Main();

        int[] number_of_tasks_running = new int[4];
        int[] start = new int[]{0, 5, 2};
        int[] end = {4, 7, 8};
        int[] query = {1, 9, 4, 3};

        test.number_of_tasks_running(number_of_tasks_running, start, end, query);

        int i = 0;
        for (int count : number_of_tasks_running){
            System.out.println(String.format("query %d, running: %d", query[i++], count));
        }
    }


    void number_of_tasks_running(
            int[] number_of_tasks_running,
            int[] start,
            int[] end,
            int[] query
    ){
        //检查参数
        if (number_of_tasks_running == null || start == null
                || end == null || query == null || start.length != end.length
                || number_of_tasks_running.length != query.length){
            throw new IllegalArgumentException();
        }

        int[] newStart = new int[start.length];
        int[] newEnd = new int[end.length];

        //排序
        sort(start, end, newStart, newEnd);

        int pos = -1;
        int count = -1;
        for (int i = 0; i < query.length; i++) {
            //二分定位，找到最后一个开始时间<=查询时间的位置
            pos = binaryQuery(newStart, query[i]);

            if (pos < 0){
                number_of_tasks_running[i] = 0;
                continue;
            }

            //计算小于等于 pos 的任务的结束时间大于查询时间的个数
            count = queryGreaterEndTimeCountByPos(newEnd, pos, query[i]);
            number_of_tasks_running[i] = Math.max(count, 0);
        }
    }

    private int queryGreaterEndTimeCountByPos(int[] newEnd, int pos, int time) {
        int count = 0;

        for (int i = pos; i >= 0; i --){
            if (newEnd[i] > time){
                count ++;
            }
        }

        return count;
    }

    /**
     * 查找第一个比 times 大或者最后一个<=的位置
     *
     * @param tasks
     * @param times
     * @return
     */
    private int binaryQuery(int[] tasks, int times) {
        int low = 0;
        int hight = tasks.length;

        int mid = (low + hight) / 2;
        int pos = -1;
        if (tasks[mid] > times){

            for (int i = mid; i >= 0; i--){
                if (tasks[i] <= times){
                    pos = i;
                    break;
                }
            }
        }else{

            for (int i = mid; i < tasks.length; i++){
                if (tasks[i] > times){
                    pos = i - 1;
                    break;
                }
            }

            if (pos < 0) {
                pos = tasks.length - 1;
            }
        }

        return pos;
    }

    /**
     * 对任务按开始时间排序
     *
     * @param start
     * @param end
     * @param newStart
     * @param newEnd
     */
    private void sort(int[] start, int[] end, int[] newStart, int[] newEnd) {
        System.arraycopy(start, 0, newStart, 0, start.length);
        System.arraycopy(end, 0, newEnd, 0, start.length);

        quickSort(newStart, newEnd, 0, start.length - 1);
    }


    /**
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     *
     * @param starts 带查找数组
     * @param low   开始位置
     * @param high  结束位置
     * @return  中轴所在位置
     */
    public  int getMiddle(int[] starts, int[] ends, int low,int high)
    {
        int temp = starts[low]; //数组的第一个作为中轴
        int temp1 = ends[low];
        while(low < high)
        {
            while(low < high && starts[high] > temp)
            {
                high--;
            }
            starts[low] = starts[high];//比中轴小的记录移到低端
            ends[low] = ends[high];
            while(low < high && starts[low] <= temp)
            {
                low++;
            }
            starts[high] = starts[low] ; //比中轴大的记录移到高端
            ends[high] = ends[low];
        }
        starts[low] = temp ; //中轴记录到尾
        ends[low] = temp1;
        return low ; // 返回中轴的位置
    }

    /**
     *
     * @param start 带排序数组
     * @param low  开始位置
     * @param high 结束位置
     */
    public  void quickSort(int[] start, int[] end, int low,int high)
    {
        if(low < high)
        {
            int middle = getMiddle(start, end, low, high); //将numbers数组进行一分为二
            quickSort(start, end, low, middle-1);   //对低字段表进行递归排序
            quickSort(start, end, middle+1, high); //对高字段表进行递归排序
        }

    }



}
