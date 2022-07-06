package dsa_assignment_06;

/**
 *
 * @author 1999k
 */
public class DSA_Assignment_06 {

    memberNode root;

    public void addRootMember(memberNode rootNode) {
        if (root == null) {
            root = rootNode;
            System.out.println("Root Member Assigned : " + rootNode.memberKey);
        } else {
            System.out.println("Root Member Is Already Assigned!");
        }
    }

    memberNode foundParent = null;

    public void findParent(memberNode rootNode, String memberKey) {
        if (rootNode == null) {
            return;
        }
        if (memberKey.equals(rootNode.memberKey)) {
            foundParent = rootNode;
        } else {
            findParent(rootNode.getLeftMemberNode(), memberKey);
            findParent(rootNode.getRightMemberNode(), memberKey);
        }
    }

    public void addLeftMember(String parentNodeKey, memberNode leftNode) {
        if (root == null) {
            System.out.println("Root Member Is Empty!");
            return;
        }
        findParent(root, parentNodeKey);
        if (foundParent == null) {
            System.out.println("No Such A Member!");
        } else {
            if (foundParent.getLeftMemberNode() == null) {
                leftNode.setParentNode(foundParent);
                foundParent.setLeftMemberNode(leftNode);
                System.out.println("Left Member : " + leftNode.memberKey + " , Set To Parent Member : " + foundParent.memberKey);
            } else {
                memberNode currentParentNode = foundParent;
                while (currentParentNode.getLeftMemberNode() != null) {
                    currentParentNode = currentParentNode.getLeftMemberNode();
                }
                leftNode.setParentNode(currentParentNode);
                currentParentNode.setLeftMemberNode(leftNode);
                System.out.println("Given Parent " + parentNodeKey + " , Left Node Not Available!");
                System.out.println("Left Member : " + leftNode.memberKey + " , Set To Parent Member : " + currentParentNode.memberKey);
            }
        }
    }

    public void addLRightMember(String parentNodeKey, memberNode rightNode) {
        if (root == null) {
            System.out.println("Root Member Is Empty!");
            return;
        }
        findParent(root, parentNodeKey);
        if (foundParent == null) {
            System.out.println("No Such A Member!");
        } else {
            if (foundParent.getRightMemberNode() == null) {
                rightNode.setParentNode(foundParent);
                foundParent.setRightMemberNode(rightNode);
                System.out.println("Right Member : " + rightNode.memberKey + " , Set To Parent Member : " + foundParent.memberKey);
            } else {
                memberNode currentParentNode = foundParent;
                while (currentParentNode.getRightMemberNode() != null) {
                    currentParentNode = currentParentNode.getRightMemberNode();
                }
                rightNode.setParentNode(currentParentNode);
                currentParentNode.setRightMemberNode(rightNode);
                System.out.println("Given Parent " + parentNodeKey + " , Right Node Not Available!");
                System.out.println("Right Member : " + rightNode.memberKey + " , Set To Parent Member : " + currentParentNode.memberKey);
            }
        }
    }

    public void exploreTreePreOrder(memberNode root) {
        if (root == null) {
            return;
        }
        System.out.print("Member : " + root.memberKey + " , ");
        exploreTreePreOrder(root.getLeftMemberNode());
        exploreTreePreOrder(root.getRightMemberNode());
    }

    public int getNodeForHeight(String nodeKey) {
        foundParent = null;
        findParent(root, nodeKey);
        if (foundParent == null) {
            return -1;
        } else {
            return heightOfTree(foundParent);
        }
    }

    public int heightOfTree(memberNode root) {
        if (root == null) {
            return -1;
        } else {
            int left = heightOfTree(root.leftMemberNode);
            int right = heightOfTree(root.rightMemberNode);
            if (left > right) {
                return left + 1;
            } else {
                return right + 1;
            }
        }
    }

    public void setMember(String parentNodeKey, memberNode memberNode) {
        if (root == null) {
            System.out.println("Root Member Is Empty!");
            return;
        }
        findParent(root, parentNodeKey);
        if (foundParent == null) {
            System.out.println("No Such A Member!");
        } else {
            if (foundParent.getLeftMemberNode() == null) {
                memberNode.setParentNode(foundParent);
                foundParent.setLeftMemberNode(memberNode);
                System.out.println("Left Member(Set Member) : " + memberNode.memberKey + " , Set To Parent Member : " + foundParent.memberKey);
            } else if (foundParent.getRightMemberNode() == null) {
                memberNode.setParentNode(foundParent);
                foundParent.setRightMemberNode(memberNode);
                System.out.println("Right Member(Set Member) : " + memberNode.memberKey + " , Set To Parent Member : " + foundParent.memberKey);
            } else if (foundParent.getRightMemberNode() != null && foundParent.getLeftMemberNode() != null) {
                addLeftMember(foundParent.memberKey, memberNode);
            }
        }
    }

    public void attachMembers(String parentNodeKey, memberNode leftNode, memberNode rightNode) {
        if (root == null) {
            System.out.println("Root Member Is Empty!");
            return;
        }
        findParent(root, parentNodeKey);
        if (foundParent == null) {
            System.out.println("No Such A Member!");
        } else {
            if (foundParent.getLeftMemberNode() == null && foundParent.getRightMemberNode() != null) {
                leftNode.setParentNode(foundParent);
                foundParent.setLeftMemberNode(leftNode);
                System.out.println("Left Member : " + leftNode.memberKey + " , Set To Parent Member : " + foundParent.memberKey);
                addLRightMember(foundParent.memberKey, rightNode);
            } else if (foundParent.getRightMemberNode() == null && foundParent.getLeftMemberNode() != null) {
                rightNode.setParentNode(foundParent);
                foundParent.setRightMemberNode(rightNode);
                System.out.println("Right Member : " + rightNode.memberKey + " , Set To Parent Member : " + foundParent.memberKey);
                addLeftMember(foundParent.memberKey, leftNode);
            } else if (foundParent.getRightMemberNode() == null && foundParent.getLeftMemberNode() == null) {
                leftNode.setParentNode(foundParent);
                foundParent.setLeftMemberNode(leftNode);
                System.out.println("Left Member : " + leftNode.memberKey + " , Set To Parent Member : " + foundParent.memberKey);
                rightNode.setParentNode(foundParent);
                foundParent.setRightMemberNode(rightNode);
                System.out.println("Right Member : " + rightNode.memberKey + " , Set To Parent Member : " + foundParent.memberKey);
            } else if (foundParent.getRightMemberNode() != null && foundParent.getLeftMemberNode() != null) {
                addLRightMember(foundParent.memberKey, rightNode);
                addLeftMember(foundParent.memberKey, leftNode);
            }
        }
    }

    public void removeMember(String parentNodeKey) {
        if (root == null) {
            System.out.println("Root Member Is Empty!");
            return;
        }
        findParent(root, parentNodeKey);
        if (foundParent == null) {
            System.out.println("No Such A Member!");
        } else {
            memberNode left = foundParent.getLeftMemberNode();
            memberNode right = foundParent.getRightMemberNode();
            if (left == null && right == null) {
                memberNode parentNode = foundParent.getParentNode();
                if (parentNode != null) {
                    if (foundParent == parentNode.getLeftMemberNode()) {
                        parentNode.setLeftMemberNode(null);
                    } else if (foundParent == parentNode.getRightMemberNode()) {
                        parentNode.setRightMemberNode(null);
                    }
                } else {
                    System.out.println("You are trying to remove Root Member");
                }
            } else if (left != null && right != null) {
                memberNode parentNode = foundParent.getParentNode();
                if (parentNode != null) {
                    if (foundParent == parentNode.getLeftMemberNode()) {
                        parentNode.setLeftMemberNode(left);
                        addLeftMember(left.memberKey, right);
                    } else if (foundParent == parentNode.getRightMemberNode()) {
                        parentNode.setRightMemberNode(right);
                        addLRightMember(right.memberKey, left);
                    }
                } else {
                    System.out.println("You are trying to remove Root Member");
                }
            } else if (left == null && right != null) {
                memberNode parentNode = foundParent.getParentNode();
                if (parentNode != null) {
                    if (foundParent == parentNode.getLeftMemberNode()) {
                        parentNode.setLeftMemberNode(right);
                    } else if (foundParent == parentNode.getRightMemberNode()) {
                        parentNode.setRightMemberNode(right);
                    }
                } else {
                    System.out.println("You are trying to remove Root Member");
                }
            } else if (left != null && right == null) {
                memberNode parentNode = foundParent.getParentNode();
                if (parentNode != null) {
                    if (foundParent == parentNode.getLeftMemberNode()) {
                        parentNode.setLeftMemberNode(left);
                    } else if (foundParent == parentNode.getRightMemberNode()) {
                        parentNode.setRightMemberNode(left);
                    }
                } else {
                    System.out.println("You are trying to remove Root Member");
                }
            }
        }
    }

    public void removeRootMember(String rootMemberKey) {
        if (root == null) {
            System.out.println("Root Member Is Empty!");
            return;
        }
        if (root.memberKey == rootMemberKey) {
            memberNode leftNode = root.getLeftMemberNode();
            memberNode rightNode = root.getRightMemberNode();
            if (leftNode.commission > rightNode.commission) {
                root = leftNode;
                addLRightMember(leftNode.memberKey, rightNode);
                System.out.println("New root is Left Node!");
            } else {
                root = rightNode;
                addLeftMember(rightNode.memberKey, leftNode);
                System.out.println("New root is Right Node!");
            }
        } else {
            System.out.println("This is not the Root Member!");
        }
    }

    public void setCommission(String memberKey, double amount) {
        if (root == null) {
            System.out.println("Root Member Is Empty!");
            return;
        }
        findParent(root, memberKey);
        if (foundParent == null) {
            System.out.println("No Such A Member!");
        } else {
            foundParent.commission = foundParent.commission + amount;
            double amountForParents = (amount * 10) / 100;
            memberNode currentNode = foundParent;
            while (currentNode.parentNode != null) {
                currentNode.parentNode.commission = currentNode.parentNode.commission + amountForParents;
                currentNode = currentNode.parentNode;
            }
            System.out.println("Set commission for all valid members!");
        }
    }

    public void calculateCommission(String memberKey) {
        foundParent = null;
        if (root == null) {
            System.out.println("Root Member Is Empty!");
            return;
        }
        findParent(root, memberKey);
        if (foundParent == null) {
            System.out.println("No Such A Member!");
        } else {
            System.out.println("Total Commission of the member " + foundParent.memberKey + " : " + foundParent.commission);
        }
    }

    public static void main(String[] args) {

        DSA_Assignment_06 ass06 = new DSA_Assignment_06();

        //Add RootMember
        System.out.println("");
        ass06.addRootMember(new memberNode("X", null));
        System.out.println("");

        //Add LeftMember
        ass06.addLeftMember("X", new memberNode("A", null));
        System.out.println("");
        ass06.addLeftMember("X", new memberNode("Y", null));
        System.out.println("");

        //Add RightMember
        ass06.addLRightMember("X", new memberNode("X1", null));
        System.out.println("");
        ass06.addLRightMember("X", new memberNode("Z", null));
        System.out.println("");
        ass06.addLRightMember("A", new memberNode("B", null));
        System.out.println("");

        ass06.addLeftMember("X1", new memberNode("K", null));
        System.out.println("");

        ass06.addLeftMember("Z", new memberNode("R", null));
        System.out.println("");

        ass06.addLRightMember("X1", new memberNode("J", null));
        System.out.println("");

//        ass06.addLRightMember("Y", new memberNode("P", null));
//        System.out.println("");
//
//        ass06.addLeftMember("A", new memberNode("O", null));
//        System.out.println("");
//
//        ass06.addLeftMember("R", new memberNode("L", null));
//        System.out.println("");
//
//        ass06.addLRightMember("B", new memberNode("Q", null));
//        System.out.println("");
//
//        ass06.addLRightMember("B", new memberNode("U", null));
//        System.out.println("");
//
//        ass06.addLRightMember("K", new memberNode("QW", null));
//        System.out.println("");
//
//        ass06.addLeftMember("K", new memberNode("OP", null));
//        System.out.println("");
        ass06.exploreTreePreOrder(ass06.root);
        System.out.println("");

        System.out.println("");
        ass06.removeMember("Z");
        System.out.println("");
        ass06.exploreTreePreOrder(ass06.root);
        System.out.println("");

        memberNode nodeLeft = new memberNode("A1", null);
        memberNode nodeRight = new memberNode("B1", null);

        nodeLeft.setLeftMemberNode(new memberNode("A11", nodeLeft));
        nodeLeft.setRightMemberNode(new memberNode("A12", nodeLeft));

        nodeRight.setLeftMemberNode(new memberNode("B11", nodeRight));
        nodeRight.setRightMemberNode(new memberNode("B12", nodeRight));

        System.out.println("");
        ass06.attachMembers("K", nodeLeft, nodeRight);
        System.out.println("");

        ass06.exploreTreePreOrder(ass06.root);
        System.out.println("");
        System.out.println("");

        ass06.setMember("Y", new memberNode("L1", null));
        System.out.println("");

        ass06.exploreTreePreOrder(ass06.root);
        System.out.println("");
        System.out.println("");

        ass06.setMember("Y", new memberNode("L2", null));
        System.out.println("");

        ass06.exploreTreePreOrder(ass06.root);
        System.out.println("");
        System.out.println("");

        ass06.setMember("A", new memberNode("L3", null));
        System.out.println("");

        ass06.exploreTreePreOrder(ass06.root);
        System.out.println("");

        System.out.println("");
        ass06.setCommission("X1", 1000);

        System.out.println("");
        ass06.setCommission("R", 1000);

        System.out.println("");
        ass06.setCommission("J", 1000);

        System.out.println("");
        ass06.setCommission("A11", 500);

        System.out.println("");
        ass06.setCommission("B12", 5000);

        System.out.println("");
        ass06.calculateCommission("X1");
        System.out.println("");

        ass06.calculateCommission("X");
        System.out.println("");

        ass06.calculateCommission("R");
        System.out.println("");

        ass06.calculateCommission("J");
        System.out.println("");

        ass06.calculateCommission("A11");
        System.out.println("");

        ass06.calculateCommission("A1");
        System.out.println("");

        ass06.calculateCommission("K");
        System.out.println("");

        ass06.calculateCommission("B1");
        System.out.println("");

        ass06.exploreTreePreOrder(ass06.root);
        System.out.println("");

        System.out.println("");
        ass06.removeRootMember("X");

        System.out.println("");
        ass06.exploreTreePreOrder(ass06.root);
        System.out.println("");

    }

}

class memberNode {

    String memberKey;
    double commission;

    memberNode leftMemberNode, rightMemberNode, parentNode;

    public memberNode(String memberKey, memberNode parentNodeM) {
        this.memberKey = memberKey;
        parentNode = parentNodeM;
        leftMemberNode = rightMemberNode = null;
        commission = 0;
    }

    public memberNode getLeftMemberNode() {
        return leftMemberNode;
    }

    public void setLeftMemberNode(memberNode leftMemberNode) {
        this.leftMemberNode = leftMemberNode;
    }

    public memberNode getRightMemberNode() {
        return rightMemberNode;
    }

    public void setRightMemberNode(memberNode rightMemberNode) {
        this.rightMemberNode = rightMemberNode;
    }

    public memberNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(memberNode parentNode) {
        this.parentNode = parentNode;
    }

}
