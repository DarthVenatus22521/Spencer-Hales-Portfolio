package spell;

import java.io.IOException;

public class Words implements ITrie {


      private int wordCount = 0;
      private int nodeCount = 1;
      private int trieHash = 0;
	  private WordNode root = new WordNode();

      char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

      StringBuilder allStrings = new StringBuilder();
      StringBuilder toAddStr = new StringBuilder();

        StringBuilder cur = new StringBuilder();
        StringBuilder output = new StringBuilder();

//      private INode root = new WordNode();
      
       
 
       public Words() {


	       
	    }

     public void add(String word) {


            WordNode current = root;

            boolean isInArray;
            int index = 0;
            int endOfWord = 0;

            for (int i = 0; i < word.length(); i++) {
                endOfWord++;
  
 	            isInArray = false;

               
		        int c = (word.charAt(i) - 'a');

                    if (current.childNode[c] != null) {

                            isInArray = true;
    
                            index = c;  
                    }



                if (isInArray) {

                    current = current.childNode[index];

                }

                else if (!isInArray) {
                    index = (c);

                    current.childNode[index] = new WordNode();

                    current = current.childNode[index];
                    nodeCount++;
                }

              }

            if (current.count == 0) {
                wordCount++;
                trieHash = trieHash + word.hashCode();
            }
            if (endOfWord == word.length()) {
            current.count++;
            }
        }

    @Override
 	public ITrie.INode find(String word) {

            WordNode current = root;

            boolean isInArray;

            int index = 0;
    
        for (int i = 0; i < word.length(); i++) {
    
                   isInArray = false;
    
                   int c = (word.charAt(i) - 'a');
    
                        if (current.childNode[c] != null) {
                                isInArray = true;
        
                                index = c;    
                        }
    
                    if (isInArray) {
                        current = current.childNode[index];
                    }
    
                    else if (!isInArray) {
                        index = -1;
                        return null;
                    }

                    isInArray = false;
            }
            if (index != -1 && current.getValue() > 0) {
                return current;
            }
    
                else {
                return null;
    
            }
}

   	public int getWordCount() {

            return wordCount;

        }


	public int getNodeCount() {

            return nodeCount;

        }


 
   	@Override

 	public String toString() {

        output = new StringBuilder();
        cur = new StringBuilder();

        WordNode node = root;
        
        toStringHelper(node);
        return output.toString();
        }

   	 
 	@Override
	 
	public int hashCode() {
            int returnHash = (trieHash + nodeCount + wordCount);

            return returnHash;

        }
	

      	@Override
	 
	public boolean equals(Object o) {      
            if (o == null) {
                return false;
            }
            if (o.getClass() != this.getClass()) {
                return false;
            }
            else {
                Words obj = (Words) o;
                if (obj.getWordCount() != this.getWordCount() || obj.getNodeCount() != this.getNodeCount()) {
                    return false;
                }
                return isEqual(obj.root, root);
            }            
        }
    
    public void toStringHelper(WordNode node) {
        if (node.getValue() > 0) {
            output.append(cur.toString() + "\n");
        }
        for (int i = 0; i < 26; i++) {
            if (node.childNode[i] != null) {
                cur.append(alphabet[i]);
                toStringHelper(node.childNode[i]);
                cur.delete(cur.length() - 1, cur.length());
            }
        }
    }

    public boolean isEqual(WordNode o, WordNode n) {
        if (o.getValue() != n.getValue()) {
            return false;
        }
        for (int i = 0; i < 26; i++) {
            if (o.childNode[i] == null && n.childNode[i] != null) {
                return false;
            }
            else if (o.childNode[i] != null && n.childNode[i] == null) {
                return false;
            }
            if (o.childNode[i] != null) {
                if (!isEqual(o.childNode[i], n.childNode[i])) {
                    return false;
                }
            }
        }
        return true;
    }
    String Control(String word) {

            String suggestion = null;


	    String delTestStr = "";
	    String transTestStr = "";
	    String altTestStr = "";
	    String InsTestStr = "";

	    INode testNode;	

    
        testNode = find(word);


        if (testNode == null) {
    		delTestStr = deleteTest(word);
	    	transTestStr = transpositionTest(word);	
	    	altTestStr = alterationTest(word);
	    	InsTestStr = insertionTest(word);
            
            if (delTestStr == "" && transTestStr == "" && altTestStr == "" && InsTestStr == "") {
                delTestStr = deleteTestTwo(word);
	    	    transTestStr = transpositionTestTwo(word);	
	    	    altTestStr = alterationTestTwo(word);
	    	    InsTestStr = insertionTestTwo(word);
            }

	    	suggestion = findNodeBestStr(delTestStr, transTestStr, altTestStr, InsTestStr);
	    }
	    else {
	        suggestion = word;
		}

	    return suggestion;
   }

    String TestTwo(String word) {

        String suggestion = null;


	    String delTestStr = "";
	    String transTestStr = "";
	    String altTestStr = "";
	    String InsTestStr = "";

	    INode testNode;	

    
        testNode = find(word);


        if (testNode == null) {
    		delTestStr = deleteTest(word);
	    	transTestStr = transpositionTest(word);	
	    	altTestStr = alterationTest(word);
	    	InsTestStr = insertionTest(word);
            
            if (delTestStr == "" && transTestStr == "" && altTestStr == "" && InsTestStr == "") {
               suggestion = "";
            }
            else {
	    	suggestion = findNodeBestStr(delTestStr, transTestStr, altTestStr, InsTestStr);
            return suggestion;
	    	}
        }
	    else {
	        suggestion = word;
		}

	    return suggestion;
   }

	
	String deleteTest(String word) {
		String returnStr = "";
		String testStr = "";
		int strCount = 0;
		int compare = 0;
		INode testNode;
		
		for (int i = 0; i < word.length(); i++) {
			StringBuilder sb = new StringBuilder(word);
			sb.deleteCharAt(i);
			testStr = sb.toString();
			testNode = find(testStr);
		
			if (testNode != null) {
				if (testNode.getValue() > 0) {
					returnStr = testStr;
					strCount = testNode.getValue();
				}
				else if (testNode.getValue() == strCount && testNode.getValue() != 0) {
					compare = returnStr.compareTo(testStr);
					if (compare > 0) {
						returnStr = testStr;
					}
				}
			}
		}

		return returnStr;	    
	}

	String transpositionTest(String word) {
		String returnStr = "";
		String testStr = "";
		char c;
		int strCount = 0;
		int compare = 0;
		INode testNode;
		
		for (int i = 0; i < word.length() - 1; i++) {
			StringBuilder sb = new StringBuilder(word);
			c = word.charAt(i);
			sb.setCharAt(i, sb.charAt(i + 1));
			sb.setCharAt((i + 1), c);
			testStr = sb.toString();
			testNode = find(testStr);
		
			if (testNode != null) {
				if (testNode.getValue() > 0) {
					returnStr = testStr;
					strCount = testNode.getValue();
				}
				else if (testNode.getValue() == strCount && testNode.getValue() != 0) {
					compare = returnStr.compareTo(testStr);
					if (compare > 0) {
						returnStr = testStr;
					}
				}
			}
		}

		return returnStr;	
	}

	String alterationTest(String word) {
		String returnStr = "";
		String testStr = "";
		int strCount = 0;
		int compare = 0;
		INode testNode;
		
		for (int i = 0; i < word.length(); i++) {
			for (int j = 0; j < alphabet.length; j++) {
			    StringBuilder sb = new StringBuilder(word);
				sb.setCharAt(i, alphabet[j]);
    			testStr = sb.toString();
	    		testNode = find(testStr);
	    	
	    		if (testNode != null) {
	    			if (testNode.getValue() > 0) {
	    				returnStr = testStr;
	    				strCount = testNode.getValue();
	    			}
	    			else if (testNode.getValue() == strCount && testNode.getValue() != 0) {
	    				compare = returnStr.compareTo(testStr);
	    				if (compare > 0) {
	    					returnStr = testStr;
	    				}
	    			}
	    		}
	  		}
		}

		return returnStr;	
	}

	String insertionTest(String word) {
		String returnStr = "";
		String testStr = "";
		int strCount = 0;
		int compare = 0;
		INode testNode;
		
		for (int i = 0; i < word.length() + 1; i++) {
			StringBuilder sb = new StringBuilder(word);
			for (int j = 0; j < alphabet.length; j++) {
				sb.insert(i, alphabet[j]);
    			testStr = sb.toString();
	    		testNode = find(testStr);
	    	
	    		if (testNode != null) {
	    			if (testNode.getValue() > 0) {
	    				returnStr = testStr;
	    				strCount = testNode.getValue();
	    			}
	    			else if (testNode.getValue() == strCount && testNode.getValue() != 0) {
	    				compare = returnStr.compareTo(testStr);
	    				if (compare > 0) {
	    					returnStr = testStr;
	    				}
	    			}
	    		}
            sb.deleteCharAt(i);
			}
		}

		return returnStr;	
	}


    String deleteTestTwo(String word) {
		String returnStr = "";
		String testStr = "";
        String testTwoStr = "";
		int strCount = 0;
		int compare = 0;
		INode testNode;
        for (int i = 0; i < word.length(); i++) {
			StringBuilder sb = new StringBuilder(word);
			sb.deleteCharAt(i);
			testStr = sb.toString();
			testNode = find(testStr);
		
			if (testNode != null) {
				if (testNode.getValue() > 0) {
					returnStr = testStr;
					strCount = testNode.getValue();
				}
				else if (testNode.getValue() == strCount && testNode.getValue() != 0) {
					compare = returnStr.compareTo(testStr);
					if (compare > 0) {
						returnStr = testStr;
					}
				}
			}
            else if (testNode == null) {
               testTwoStr = TestTwo(testStr);
               compare = returnStr.compareTo(testTwoStr);
					if (compare < 0) {
						returnStr = testTwoStr;
					}
            }
		}
		return returnStr;	    
	}

	String transpositionTestTwo(String word) {
		String returnStr = "";
		String testStr = "";
        String testTwoStr = "";
		char c;
        char r;
		int strCount = 0;
		int compare = 0;
		INode testNode;
        for (int i = 0; i < word.length() - 1; i++) {
			StringBuilder sb = new StringBuilder(word);
			c = word.charAt(i);
			sb.setCharAt(i, sb.charAt(i + 1));
			sb.setCharAt((i + 1), c);
			testStr = sb.toString();
			testNode = find(testStr);
		
			if (testNode != null) {
				if (testNode.getValue() > 0) {
					returnStr = testStr;
					strCount = testNode.getValue();
				}
				else if (testNode.getValue() == strCount && testNode.getValue() != 0) {
					compare = returnStr.compareTo(testStr);
					if (compare > 0) {
						returnStr = testStr;
					}
				}
			}
            else if (testNode == null) {
               testTwoStr = TestTwo(testStr);
               compare = returnStr.compareTo(testTwoStr);
					if (compare < 0) {
						returnStr = testTwoStr;
					}
            }
		}
		return returnStr;	
	}

	String alterationTestTwo(String word) {
		String returnStr = "";
		String testStr = "";
        String testTwoStr = "";
		int strCount = 0;
		int compare = 0;
		INode testNode;
        for (int i = 0; i < word.length(); i++) {
			for (int j = 0; j < alphabet.length; j++) {
			    StringBuilder sb = new StringBuilder(word);
				sb.setCharAt(i, alphabet[j]);
    			testStr = sb.toString();
	    		testNode = find(testStr);
	    	
	    		if (testNode != null) {
	    			if (testNode.getValue() > 0) {
	    				returnStr = testStr;
	    				strCount = testNode.getValue();
	    			}
	    			else if (testNode.getValue() == strCount && testNode.getValue() != 0) {
	    				compare = returnStr.compareTo(testStr);
	    				if (compare > 0) {
	    					returnStr = testStr;
	    				}
	    			}
	    		}
               else if (testNode == null) {
                   testTwoStr = TestTwo(testStr);
                   compare = returnStr.compareTo(testTwoStr);
	    				if (compare < 0) {
	    					returnStr = testTwoStr;
	    				}
                }
	  		}
		}
		return returnStr;	
	}

	String insertionTestTwo(String word) {
		String returnStr = "";
		String testStr = "";
        String testTwoStr = "";
		int strCount = 0;
		int compare = 0;
		INode testNode;
        for (int i = 0; i < word.length() + 1; i++) {
			StringBuilder sb = new StringBuilder(word);
			for (int j = 0; j < alphabet.length; j++) {
				sb.insert(i, alphabet[j]);
    			testStr = sb.toString();
	    		testNode = find(testStr);
	    	
	    		if (testNode != null) {
	    			if (testNode.getValue() > 0) {
	    				returnStr = testStr;
	    				strCount = testNode.getValue();
	    			}
	    			else if (testNode.getValue() == strCount && testNode.getValue() != 0) {
	    				compare = returnStr.compareTo(testStr);
	    				if (compare > 0) {
	    					returnStr = testStr;
	    				}
	    			}
	    		}
                else if (testNode == null) {
                   testTwoStr = TestTwo(testStr);
                   compare = returnStr.compareTo(testTwoStr);
	    				if (compare < 0) {
	    					returnStr = testTwoStr;
	    				}
                }
                sb.deleteCharAt(i);
			}
		}
		return returnStr;	
	}

	String findNodeBestStr(String delTestStr, String transTestStr, String altTestStr, String insTestStr) {
		INode delNode = find(delTestStr);
		INode transNode = find(transTestStr);
		INode altNode = find(altTestStr);
		INode insNode = find(insTestStr);

        String testStr = "";
        int testCount = 0;

        if (delNode != null) {
			testStr = delTestStr;
			testCount = delNode.getValue();
        }
        if (transNode != null) {
			if (transNode.getValue() >= testCount) {
				if (transNode.getValue() == testCount) {
					int compare = testStr.compareTo(transTestStr);
					if (compare > 0) {
						testStr = transTestStr;
					}				
				}
				else if (transNode.getValue() > testCount) {
					testStr = transTestStr;
					testCount = transNode.getValue();
				}
			}
        }
        if (altNode != null) {
			if (altNode.getValue() >= testCount) {
				if (altNode.getValue() == testCount) {
					int compare = testStr.compareTo(altTestStr);
					if (compare > 0) {
						testStr = altTestStr;
					}				
				}
				else if (altNode.getValue() > testCount) {
					testStr = altTestStr;
					testCount = altNode.getValue();
				}
			}
        }
        if (insNode != null) {
			if (insNode.getValue() >= testCount) {
				if (insNode.getValue() == testCount) {
					int compare = testStr.compareTo(insTestStr);
					if (compare > 0) {
						testStr = insTestStr;
					}				
				}
				else if (insNode.getValue() > testCount) {
					testStr = insTestStr;
					testCount = insNode.getValue();
				}
			}
        }
		return testStr;
//		}
		
	}

public class WordNode implements ITrie.INode {


	public WordNode() {



        }



 	public int getValue() {

             return count;

        }



        public char getLetter() {

            return letter;

        }


        
        char letter = 'A';

        int count = 0;

        WordNode[] childNode = new WordNode[26];
    }


}

