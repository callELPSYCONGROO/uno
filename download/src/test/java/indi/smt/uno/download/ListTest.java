package indi.smt.uno.download;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author 無痕剑
 * @date 2019/4/16 0:50
 */
public class ListTest {

	@Test
	public void partitionTest() {
		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		System.out.println(ListUtils.partition(integers, 5));
	}
}
