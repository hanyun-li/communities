package file.hanyun.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious; //前一页的按钮
    private boolean showFirstPrevious;  //首页的按钮
    private boolean showNext;  //后一页的按钮
    private boolean showEndPage;  //尾页的按钮
    private Integer currentPage;  //当前页
    private List<Integer> pages;  //所有分页的数字的数组
    private Integer totalPage;  //总页数

    public void setPagination(Integer recordCount, Integer size, Integer currentPage) {

        if (recordCount % size == 0) {
            totalPage = recordCount / size;  //总记录数刚好是分页数的倍数
        } else {
            totalPage = recordCount / size + 1;  //总记录数不是分页数的倍数
        }

        this.currentPage = currentPage;

        pages = new ArrayList<>();
        pages.add(currentPage);  //添加当前页
        for (int i = 1; i <= 3; i++) {
            if (currentPage - i > 0) {
                pages.add(0, currentPage - i);
            }

            if (currentPage + i <= totalPage) {
                pages.add(currentPage + i);
            }
        }

        //如果当前页数为1，则不显示前一页的按钮
        if (currentPage == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        //如果当前页数等于总页数，则不显示后一页的按钮
        if (currentPage == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //如果显示的页数集合中包含第一页，则不展示首页的按钮
        if (pages.contains(1)) {
            showFirstPrevious = false;
        } else {
            showFirstPrevious = true;
        }

        //如果显示的页数集合中包含最后一页，则不展示尾页的按钮
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }


    }
}
