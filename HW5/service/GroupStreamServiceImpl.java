package service;

import data.GroupStream;
import data.comparator.GroupStreamComparator;

import java.util.List;
import java.util.Collections;

public class GroupStreamServiceImpl implements StreamService<GroupStream, Integer> {
    @Override
    public void streamSort(List<GroupStream> groupStream) {
        Collections.sort(groupStream, new GroupStreamComparator());
    }

    @Override
    public GroupStream save(GroupStream entity) {
        return null;
    }

    @Override
    public GroupStream findById(Integer id) {
        return null;
    }
}
