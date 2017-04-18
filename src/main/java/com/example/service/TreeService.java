package com.example.service;

import com.example.entity.tree.Node;
import com.example.entity.tree.VirtualDataGenerator;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by yonglang on 2017/4/18.
 */
@Component
public class TreeService {

    public String getTree(){
        List dataList = VirtualDataGenerator.getVirtualResult();
        // 节点列表（散列表，用于临时存储节点对象）
        HashMap nodeList = new HashMap();
        // 根节点
        Node root = null;
        // 根据结果集构造节点列表（存入散列表）
        for(Iterator iterator = dataList.iterator(); iterator.hasNext();){
            Map dataRecord = (Map) iterator.next();
            Node node = new Node();
            node.id = (String) dataRecord.get("id");
            node.text = (String) dataRecord.get("text");
            node.parentId = (String) dataRecord.get("parentId");
            nodeList.put(node.id, node);
        }
        // 构造无序的多叉树
        Set entrySet = nodeList.entrySet();
        for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
            Node node = (Node) ((Map.Entry) iterator.next()).getValue();
            if (node.parentId == null || node.parentId.equals("")) {
                root = node;
            } else {
                ((Node) nodeList.get(node.parentId)).addChild(node);
            }
        }
        // 输出无序的树形菜单的JSON字符串
        System.out.println(root.toString());
        // 对多叉树进行横向排序
        root.sortChildren();
        // 输出有序的树形菜单的JSON字符串
        System.out.println(root.toString());
        return root.toString();
    }

}
