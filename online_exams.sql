/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云centos7.9（11.5）
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 8.130.115.225:3306
 Source Schema         : online_exams

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 24/04/2023 22:28:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题目',
  `optionA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optionB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optionC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `optionD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '答案：选择题的话为ABCD,填空题为字符串',
  `types` int(1) NULL DEFAULT NULL COMMENT '类型：计网1，操作系统2，计组3，数据结构4',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附加图片',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题目备注',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `rank` int(10) NULL DEFAULT 0 COMMENT '熟练度',
  `isPublic` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否公开：1是，0否（默认）',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `isDeleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删除1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, '在OSI七层结构模型中，处于数据链路层与运输层之间的是()', '物理层', '网络层', '会话层', '表示层', 'B', 1, NULL, NULL, 3, 2, 0, '2023-04-17 11:28:59', 0);
INSERT INTO `question` VALUES (2, '若网络形状是由站点和连接站点的链路组成的一个闭合环,则称这种拓扑结构为()', '星形拓扑', '总线拓扑', '环形拓扑', '树形拓扑', 'C', 1, NULL, NULL, 3, 3, 0, '2023-04-17 11:29:00', 0);
INSERT INTO `question` VALUES (3, '世界上很多国家都相继组建了自己国家的公用数据网，现有的公用数据网大多采用()', '分组交换方式', '报文交换方式', '电路交换方式', '空分交换方式', 'A', 1, NULL, NULL, 3, 2, 0, '2023-04-17 11:29:01', 0);
INSERT INTO `question` VALUES (4, '因特网在通信子网内实现数据报操作方式对端系统()', '只提供数据报服务', '只提供虚电路服务', '提供数据报和虚电路服务', '不提供服务', 'C', 1, NULL, NULL, 3, 1, 1, '2023-04-17 11:29:03', 0);
INSERT INTO `question` VALUES (5, '在OSI中，物理层存在四个特性。其中，通信接口所用接线器的形状和尺寸属于()', '机械特性', '电气特性', '功能特性', '过程特性', 'A', 1, NULL, NULL, 3, 0, 0, '2023-04-17 11:29:03', 0);
INSERT INTO `question` VALUES (6, '在计算机网络通信系统中，一般要求误码率低于()', '10-4', '10-5', '10-6', '10-7', 'C', 1, NULL, NULL, 3, 0, 0, '2023-04-17 11:29:03', 0);
INSERT INTO `question` VALUES (7, '局域网具有的几种典型的拓扑结构中，一般不含()', '星型', '环型', '总线型', '全连接网型', 'D', 1, NULL, NULL, 3, 0, 0, '2023-04-24 22:25:06', 0);
INSERT INTO `question` VALUES (8, '网络协议主要要素为_________。', NULL, NULL, NULL, NULL, '语法、语义、同步。', 1, NULL, NULL, 3, 1, 1, '2023-04-17 11:29:08', 0);
INSERT INTO `question` VALUES (9, '通信系统必须具备的三个基本要素是()', '终端、电缆、计算机', '信号发生器、通信线路、信号接收设备', '信源、通信媒体、信宿', '终端、通信设施、接收设备', 'C', 1, NULL, NULL, 3, 2, 0, '2023-04-17 11:29:07', 0);
INSERT INTO `question` VALUES (10, '计算机网络通信系统是()', '电信号传输系统', '文字通信系统', '信号通信系统', '数据通信系统', 'D', 1, NULL, NULL, 3, 0, 1, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (11, '常用的传输介质中，带宽最宽、信号传输衰减最小、抗干扰能力最强的一类传输介质是()', '光纤', '双绞线', '同轴电缆', '无线信道', 'A', 1, NULL, NULL, 3, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (12, '已知头指针h指向一个带头结点的非空单循环链表，结点结构为下图所示，其中next是指向直接后继结点的指针，p是尾指针，q是临时指针。现要删除该链表的第一个元素，正确的语句序列是', 'h-> next= h-> next -> next;q= h-> next; free(q)', 'q=h-> next;h-> next= h-> next -> next; free(q)', 'q=h-> next; h-> next=q -> next; if(p != q)p = h; free(q)', 'q=h-> next; h-> next=q -> next; if(p == q)p= h; free(q)', 'D', 4, '0df43ce7-f7cb-4e85-a2d4-fc3d39707ccd.PNG', NULL, 12, 2, 1, '2023-04-17 11:01:13', 0);
INSERT INTO `question` VALUES (13, '已知初始为空的队列Q的一端仅能进行入队操作，另外一端既能进行入队操作又能进行出队操作。若Q的入队序列是1,2,3,4,5，则不能得到的出队序列是', '5,4,3,1,2', '5,3,1,2,4', '4,2,1,3,5', '4,1,3,2,5', 'D', 4, NULL, NULL, 12, 2, 1, '2023-04-17 11:01:15', 0);
INSERT INTO `question` VALUES (14, '已知二维数组A按行优先方式存储,每个元素占用1个存储单元。若元素A[0] [0]的存储地址是100，A[3][3]的存储地址是220，则元素A[5][5]的存储地址是', '295', '300', '301', '306', 'B', 4, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (15, '某森林F对应的二叉树为T,若T的先序遍历序列是a,b,d,c,e,g, f,中序遍历序列是b, d, a,e,g,c,f,则F中树的棵数是', '1', '2', '3', '4', 'C', 4, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (16, '若某二叉树有5个叶结点，其权值分别为10, 12, 16, 21, 30,则其最小的带权路径长度(WPL)是', '89', '200', '208', '289', 'B', 4, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (17, '给定平衡二叉树如下图所示，插入关键字23后，根中的关键字是', '16', '20', '23', '25', 'D', 4, 'f9d2effd-16dd-498a-8527-e82c9112c6eb.PNG', NULL, 12, 0, 1, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (18, '给定如下有向图，该图的拓扑有序序列的个数是', '1', '2', '3', '4', 'A', 4, '63339909-926e-405c-a7aa-bb2b15b18454.PNG', NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (19, '使用Djkstra算法求下图中从顶点1到其余各顶点的最短路径，将当前找到的从顶点1到顶点2,3, 4, 5的最短路径长度保存在数组dist中，求出第二条最短路径后，dist 中的内容更新为', '26,3,14,6', '25,3,14,6', '21,3,14,6', '15,3,14,6', 'C', 4, '3171712e-60ae-4c8c-97c1-c9cf8cded5f5.PNG', NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (20, '在一棵高度为3的3阶B树中，根为第1层，若第2层中有4个关键字，则该树的结点个数最多是', '11', '10', '9', '8', 'A', 4, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (21, '设数组S[= {93, 946, 372, 9, 146, 151, 301, 485, 236, 327, 43, 892}，采用最低位优先(LSD)基数排序将S排列成升序序列。第1趟分配、收集后，元素372之前、之后紧邻的元素分别是', '43,892', '236,301', '301,892', '485,301', 'C', 4, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (22, '下列指令中，只能在内核态执行的是', 'trap 指令', 'I/O 指令', '数据传送指令', '设置断点指令', 'B', 2, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (23, '下列操作中，操作系统在创建新进程时，必须完成的是( )。\nI.申请空白的进程控制块\nII. 初始化进程控制块\nIII.设置进程状态为执行态', '仅I', '仅I、II', '仅I、III', '仅II、III', 'B', 2, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:22', 0);
INSERT INTO `question` VALUES (24, '下列内核的数据结构或程序中，分时系统实现时间片轮转调度需要使用的是( )。\nI.进程控制块\nII.时钟中断处理程序\nIII. 进程就绪队列\nIV.进程阻塞队列', '仅II、III', '仅I、IV', '仅I、 II、III', '仅I、II、IV', 'C', 2, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (25, '某系统中磁盘的磁道数为200 (0~199),磁头当前在184号磁道上。用户进程提出的磁盘访问请求对应的磁道号依次为184, 187, 176, 182, 199。若采用最短寻道时间优先调度算法(SSTF)完成磁盘访问，则磁头移动的距离(磁道数)是', '37', '38', '41', '42', 'C', 2, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (26, '下列事件中，可能引起进程调度程序执行的是( )。\nI.中断处理结束\nII. 进程阻塞\nIII.进程执行结束\nIV.进程的时间片用完', '仅I、III', '仅II、IV', '仅III、IV', 'I、II、III、 IV', 'D', 2, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (27, '某请求分页存储系统的页大小为4KB， 按字节编址。系统给进程P分配2个固定的页框并采用改进型Clock置换算法，进程P页表的部分内容如下表所示。\n若P访问虚拟地址为02A01H的存储单元，则经地址变换后得到的物理地址是', '00A01H', '20A01H', '60A01H', '80A01H', 'C', 2, 'fd8cb443-2c5f-4d7c-9690-7c23ed6400ed.PNG', NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (28, '下图描述的协议要素是\nI．语法 II．语义 III．时序', '仅I', '仅II', '仅III', ' I、II和III', 'C', 1, '3c3cddaa-3a85-4272-a996-278647315e73.PNG', NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (29, '下图所示的网络中，冲突域和广播域的个数分别是', '2,2', '2,4', '4,2', '4,4', 'C', 1, 'd9a0ffbb-3c27-440b-9dbf-d23e61c6d31d.PNG', NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (30, '某IEEE 802.11无线局域网中，主机H与AP之间发送或接收CSMA/CA帧的过程如下图所示。在H或AP发送帧前所等待的帧间间隔时间(IFS）中，最长的是', 'IFS1', 'IFS2 ', 'IFS3', 'IFS4', 'A', 1, 'e7849497-5152-45ec-b3ea-c3c3ff98ab33.PNG', NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (31, '若主机甲与主机乙已建立一条TCP连接，最大段长(MSS）为1KB,往返时间(RTT)为2ms，则在不出现拥塞的前提下，拥塞窗口从8KB增长到32KB所需的最长时间是', '4ms', '8ms', '24ms', '48ms', 'D', 1, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (32, '若主机甲与主机乙建立TCP连接时，发送的SYN段中的序号为1000，在断开连接时，甲发送给乙的FIN段中的序号为5001，则在无任何重传的情况下，甲向乙已经发送的应用层数据的字节数为', '4002', '4001', '4000', '3999', 'C', 1, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (33, '假设主机甲采用停-等协议向主机乙发送数据帧，数据帧长与确认帧长均为1000B，数据传输速率是10kbps，单项传播延时是200ms。则甲的最大信道利用率为', '80%', '66.7%', '44.4%', '40%', 'D', 1, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (34, '下列关于虚电路网络的叙述中，错误的是', '可以确保数据分组传输顺序', '需要为每条虚电路预分配带宽', '建立虚电路时需要进行路由选择', '依据虚电路号(VCID)进行数据分组转发', 'B', 1, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (35, '计算器浮点运算速度为93.0146PFLOPS，这说明该计算器每秒完成的浮点数操作次数为', '9.3×10^13', '9.3×10^15', '9.3千万亿次', '9.3亿亿次', 'D', 3, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (36, '下列数值中，不能用IEEEE754浮点精确表示的是', '1.2', '1.25', '2.0', '2.5', 'A', 3, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (37, '某计算机的存储总线中有24位地址线和32位数据线，按字编址，字长为32位，若000000H~3FFFFFH为RAM区，则需要512K×8位的RAM芯片数为', '8', '16', '32', '64', 'C', 3, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (38, '若计算机主存地址为32位，按字节编址，cache数据区大小为32KB，主存块大小为32B，采用直接映射方法和回写(Write Back)策略，则cache行的位数至少是', '275', '274', '258', '257', 'A', 3, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (39, '下列存储器中，汇编语言程序员可见的是', '指令寄存器', '微指令寄存器', '基址寄存器', '标志状态寄存器', 'D', 3, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (40, '下列选项中不属于I/O接口的是', '磁盘驱动器', '打印机适配器', '网络控制器', '可编程中断控制器', 'A', 3, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (41, '下列关于总线的叙述中，错误的是', '总线是在两个或多个部件之间进行数据交换的传输介质', '同步总线由时钟信号定时，时钟频率不一定等于工作频率', '异步总线由握手信号定时，一次握手过程完成一位数据交换', '突发(Burst)传送总线事务可以在总线上连续传送多个数据', 'C', 3, NULL, NULL, 12, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (42, '若系统中n (n>=2) 个进程，每个进程均需使用某类临界资源2个，则系统不会发生死锁所需的该类资源总至少', '2', 'n', 'n+1', '2n', 'C', 2, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (43, '通过系统调用完成的操作是', '页面置换', '进程调度', '创建新进程', '生成随机整数', 'C', 2, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (44, '删除一个文件后，下列不会发生变化的是', '快捷方式被删除', '文件控制块被回收', '磁盘空间被释放', '删除目录', 'A', 2, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (45, '二级页表中基址寄存器存放的是', '一级页表物理地址', '二级页表物理地址', '一级页表虚拟地址', '二级页表虚拟地址', 'A', 2, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (46, '不能在用户态下发生的是', 'trap指令', '系统调用', 'I/O指令', '库函数', 'C', 2, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (47, '将一个10x10对称矩阵M的上三角部分的元素m,j (1≤i≤j≤10)按列优先存入C语言的一维数组N中，元素m7,2在N中的下标是', '15', '16', '22', '23', 'C', 4, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (48, '对空栈S进行Push和Pop操作，入栈序列为a, b, c, d,e,经过Push, Push, Pop, Push, Pop, Push,Push, Pop操作后得到的出栈序列是', 'b,a,c', 'b,a,e', 'b,c,a', 'b,c,e', 'D', 4, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (49, '对于任意-棵高度为5且有10个结点的二叉树，若采用顺序存储结构保存,每个结点占1个存储单元(仅存放结点的数据信息),则存放该二叉树需要的存储单元数量至少是', '31', '16', '15', '10', 'A', 4, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (50, '已知森林F及与之对应的二叉树T,若F的先根遍历序列是a, b,c,d,e,f,中根遍历序列是b,a,d,f,e,c，则T的后根遍历序列是', 'b,a,d,f,e,c', 'b,d,f,e,c,a', 'b,f,e,d,c,a', 'f,e,d,c, b, a', 'C', 4, NULL, NULL, 13, 3, 1, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (51, '下列给定的关键字输入序列中，不能生成如下二叉排序树的是', '4,5,2,1,3', '4,5,1,2,3', '4,2,5,3,1', '4,2,1,3,5', 'B', 4, 'b8ba62bb-5852-4165-87d4-6d366464eb57.PNG', NULL, 13, 2, 1, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (52, '修改递归方式实现的图的深度优先搜索(DFS) 算法，将输出(访问)顶点信息的语句移到退出递归前(即执行输出语句后立刻退出递归)。采用修改后的算法遍历有向无环图G, 若输出结果中包含G中的全部顶点，则输出的顶点序列是G的', '拓扑有序序列', '逆拓扑有序序列', '广度优先搜索序列', '深度优先搜索序列', 'B', 4, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (53, '已知无向图G如下所示，使用克鲁斯卡尔(Kruskal) 算法求图G的最小生成树,加到最小生成树中的边依次是', '(b,f), (b,d), (a,e), (c,e),(b,e)\n', '(b,f), (b,d), (b,e), (a,e),(c,e)', '(a,e), (b,e), (c,e), (b,d),(b,f)', '(a,e), (c,e), (b,e), (b,f),(b,d)', 'A', 4, 'f58a037e-66af-4c63-bcb2-c8c24df83585.PNG', NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (54, '若使用AOE网估算工程进度，则下列叙述中正确的是', '关键路径是从原点到汇点边数最多的一条路径', '关键路径是从原点到汇点路径长度最长的路径', '增加任一关键活动的时间不会延长工程的工期', '缩短任一关键活动的时间将会缩短工程的工期', 'B', 4, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (55, '下列关于大根堆(至少含2个元素)的叙述中，正确的是( )。\nI.可以将堆视为一棵完全二叉树\nII.可以采用顺序存储方式保存堆\nIII.可以将堆视为一棵二叉排序树\nIV. 堆中的次大值一定在根的下一层', '仅I、II', '仅II、III', '仅I、II和IV', 'I、III和IV', 'C', 4, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (56, '依次将关键字5,6,9, 13, 8,2, 12, 15插入初始为空的4阶B树后，根结点中包含的关键字是', '8', '6,9', '8,13', '9,12', 'B', 4, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (57, '对大部分元素已有序的数组进行排序时，直接插入排序比简单选择排序效率更高，其原因是()。\n\nI.直接插入排序过程中元素之间的比较次数更少\nII.直接插入排序过程中所需要的辅助空间更少\nIII.直接插入排序过程中元素的移动次数更少', '仅I', '仅III', '仅I、II', 'I、II和III', 'A', 4, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (58, '下列关于冯`诺依曼结构计算机基本思想的叙述中，错误的是', '程序的功能都通过中央处理器执行指令实现', '指令和数据都用二进制表示，形式上无差别', '指令按地址访问，数据都在指令中直接给出', '程序执行前，指令和数据需预先存放在存储器中', 'C', 3, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (59, '考虑以下C语言代码:\nunsigned short usi = 65535;short si = usi;\n执行上述程序段后，si 的值是', '-1', '-32767', '-32768', '-65535', 'A', 3, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (60, '下列关于缺页处理的叙述中，错误的是', '缺页是在地址转换时CPU检测到的一种异常', '缺页处理由操作系统提供的缺页处理程序来完成', '缺页处理程序根据页故障地址从外存读入所缺失的页', '缺页处理完成后回到发生缺页的指令的下一条指令执行', 'D', 3, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (61, '在OSI参考模型中，R1、Switch、Hub实现的最高功能层分别是', '2、2、1', '2、2、2', '3、2、1', '3、2、2', 'C', 1, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (62, '若连接R2和R3链路的频率带宽为8 kHz，信噪比为30 dB，该链路实际数据传输速率约为理论最大数据传输速率的50％，则该链路的实际数据传输速率约是', '8 kbps', '20 kbps', '40 kbps ', '80 kbps', 'C', 1, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (63, '若主机H2向主机H4发送1个数据帧，主机H4向主机H2立即发送一个确认帧，则除H4外，从物理层上能够收到该确认帧的主机还有', '仅H2', '仅H3 ', '仅H1、H2', '仅H2、H3', 'D', 1, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (64, '若Hub再生比特流过程中，会产生1.535μs延时，信号传播速度为200 m/μs，不考虑以太网帧的前导码，则H3与H4之间理论上可以相距的最远距离是', '200 m', '205 m', '359 m', '512 m', 'B', 1, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (65, '假设R1、R2、R3采用RIP协议交换路由信息，且均已收敛。若R3检测到网络201.1.2.0/25不可达，并向R2通告一次新的距离向量，则R2更新后，其到达该网络的距离是', '2', '3　', '16', '17', 'B', 1, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (66, '假设连接R1、R2和R3之间的点对点链路使用201.1.3.x/30地址，当H3访问Web服务器S时，R2转发出去的封装HTTP请求报文的IP分组的源IP地址和目的IP地址分别是', '192.168.3．251，130.18.10.1', '192.168.3．251，201.1．3.9', '201.1．3.8，130.18.10.1', '201.1．3.10，130.18.10.1', 'D', 1, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (67, '假设H1与H2的默认网关和子网掩码均分别配置为192.168.3.1和255.255.255.128，H3与H4的默认网关和子网掩码均分别配置为192.168.3.254和255.255.255.128，则下列现象中可能发生的是', 'H1不能与H2进行正常IP通信', 'H2与H4均不能访问Internet', 'H1不能与H3进行正常IP通信', 'H3不能与H4进行正常IP通信', 'C', 1, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (68, '假设所有域名服务器均采用迭代查询方式进行域名解析。当H4访问规范域名为www.abc.xyz.com的网站时，域名服务器201.1.1.1在完成该域名解析过程中，可能发出DNS查询的最少和最多次数分别是', '0，3', '1，3 ', '0，4　', '1，4', 'C', 1, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (69, '已知一棵二叉树的树形如下图所示，其后序序列为e，a，c，b，d，g，f，树中与结点a同层的结点是', 'c', 'd', 'f', 'g', 'B', 4, '682890ad-ed05-4e23-bcb7-4d3e01c77298.PNG', NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (70, '某程序运行于一个由L1、L2两级Cache以及主存组成的存储系统，L1Cache和L2Cache的命中率分别为50%和80%，则整个存储系统Cache的命中率是', '65%', ' 80%', ' 90%', ' 95%', 'C', 3, NULL, NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (71, '已知双向循环链表的结点构造如图，在链表中由指针q所指结点的后面插入指针为p的结点的过程是依次执行', 'p->llink=q; p->rlink=q->rlink; q->rlink=p; q->llink=p', 'p->llink=q; p->rlink=q->rlink; q->rlink=p; q->rlink->llink=p', 'p->llink=q; p->rlink=q->rlink; q->rlink->llink=p; q->rlink=p', 'p->llink=q; p->rlink=q->rlink; q->rlink=p; p->llink->llink=p', 'C', 4, '2d4c92f6-4c61-4a02-9dda-7b0c1ad123d9.PNG', NULL, 13, 0, 0, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (72, '若将一棵树 T 转化为对应的二叉树 BT，则下列对 BT 的遍历中，其遍历序列与 T 的后根遍历序列相同的是（）', '先序遍历', '中序遍历', '后序遍历', '按层遍历', 'B', 4, NULL, NULL, 4, 2, 1, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (73, '用有向无环图描述表达式 (x y)((x y) / x) ，需要的顶点个数至少是（）', '5', '6', '8', '9', 'A', 4, NULL, NULL, 4, 1, 1, '2023-04-24 22:25:09', 0);
INSERT INTO `question` VALUES (74, '已知头指针h指向一个带头结点的非空单循环链表，结点结构为下图所示，其中next是指向直接后继结点的指针，p是尾指针，q是临时指针。现要删除该链表的第一个元素，正确的语句序列是', 'h-> next= h-> next -> next;q= h-> next; free(q)', 'q=h-> next;h-> next= h-> next -> next; free(q)', 'q=h-> next; h-> next=q -> next; if(p != q)p = h; free(q)', 'q=h-> next; h-> next=q -> next; if(p == q)p= h; free(q)', 'D', 4, '0df43ce7-f7cb-4e85-a2d4-fc3d39707ccd.PNG', NULL, 3, 2, 0, '2023-04-24 22:25:09', 0);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contributions` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (3, 'zyzyzy', '1234567', NULL, '779e3dc1-69d8-41fe-b84d-152d77d9e35e.png', 00000000000);
INSERT INTO `t_user` VALUES (4, 'ababab', '1234567', NULL, '8a37f546-00b8-4249-b25c-256f8c0cee1d.jpg', 00000000000);
INSERT INTO `t_user` VALUES (12, 'lsylsy', '123456', NULL, 'acfff038-d57e-43b1-8ff3-30b28995a40d.jpg', 00000000001);
INSERT INTO `t_user` VALUES (13, 'jacker', '123456', NULL, '4febd3b5-3282-48f5-8c5c-3343e7d0f54e.jpg', 00000000000);
INSERT INTO `t_user` VALUES (14, 'aa', '123456', NULL, NULL, 00000000000);
INSERT INTO `t_user` VALUES (15, 'bb', '123456', NULL, NULL, 00000000000);
INSERT INTO `t_user` VALUES (16, 'cc', '123456', NULL, NULL, 00000000000);
INSERT INTO `t_user` VALUES (17, 'dd', '123456', NULL, NULL, 00000000000);
INSERT INTO `t_user` VALUES (18, 'ee', '123456', NULL, NULL, 00000000000);
INSERT INTO `t_user` VALUES (19, 'gg', '123456', NULL, NULL, 00000000000);
INSERT INTO `t_user` VALUES (20, 'ff', '123456', NULL, NULL, 00000000000);
INSERT INTO `t_user` VALUES (21, 'hh', '123456', NULL, NULL, 00000000000);
INSERT INTO `t_user` VALUES (22, 'jj', '123456', NULL, NULL, 00000000000);
INSERT INTO `t_user` VALUES (23, 'xiaolin', '123456', NULL, NULL, 00000000000);

SET FOREIGN_KEY_CHECKS = 1;
