FIRE 2011 SMS Task Training Data Set
Released: 7th July 2011
Version: 1.0
Contact: fire2011smstask@gmail.com
WebPage: http://www.isical.ac.in/~clia/faq-retrieval/faq-retrieval.html
-------------------------------------


This folder contains the training data for the SMS task at FIRE 2011. 

There are two folders in this directory:
1. FAQs
2. SMS_Queries

The FAQs folder has three sub-directories, one corresponding to each language that we have FAQs for. The 'English' subdirectory has a file eng.xml containing all the FAQs that we have for the 'English' language. (Similar folders exist for Hindi and Malayalam).

The XML has an outer <FAQS> tag within which each FAQ would be in the format as below:

<FAQ>
<FAQID>FAQ_ID</FAQID>
<DOMAIN>FAQ_DOMAIN</DOMAIN>
<QUESTION>FAQ_QUESTION</QUESTION>
<ANSWER>FAQ_ANSWER</ANSWER>
</FAQ>

There are 7251 FAQs in English, 1994 in Hindi and 681 in Malayalam. 
The FAQs are common for all the three tasks (Monolingual, CrossLingual and Multi-lingual retrieval).

In the SMS_Queries folder, we have three separate folders, one for each task. The XML files are within the appropriate folders. 

Each of the SMS XML files has an outer <QUERIES> tag and each SMS within is of the following format:

<SMS>
<SMS_QUERY_ID>QUERY_ID</SMS_QUERY_ID>
<SMS_TEXT>QUERY_TEXT</SMS_TEXT>
<MATCHES>
<ENGLISH>ENG_MATCHED_FAQID1,ENG_MATCHED_FAQID2</ENGLISH>
<MALAYALAM>MALAYALAM_MATCHED_FAQID1</MALAYALAM>
<HINDI>HINDI_MATCHED_FAQID1</HINDI>
</MATCHES>
</SMS>

For each SMS, the corresponding FAQ matches are within the <MATCHES> tag (and in language tags within the MATCHES tag). If there are multiple matches in a language, they are have been provided, seperated by commas. The matches are based on the  content of the <QUESTION> tag, that is the "text of the question" and not the "answers" of the FAQ. However, the partcipants may utilize the content of the <ANSWERS> to aid in matching.

For the SMS queries for the mono-lingual tasks, only the same language matches (matches of the same language as the query is) are provided. 

For cross-lingual and multi-lingual, all necessary matches are provided. 

Each of the SMS query files contain a few out of domain queries (i.e queries that will not match any FAQ).