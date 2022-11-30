public class Magpie {
    private static String pastResponses = "";
    public void greeting() {
        System.out.println("Hello, select one of the following options:\n1) Start chatting\n2) Exit");
    }

    public void emptyResponse(String statement1) {
        if(statement1.length() < 0) {
            System.out.println("Say something, please.");
            pastResponses = "";
        }
    }

    public void pronounResponses(String statement2) {
        pastResponses = statement2;
        if (statement2.contains("Mr.")) {
            System.out.println("He sounds like a very nice teacher!");
        } else if (statement2.contains("Mrs.") || (statement2.contains("Ms."))){
            System.out.println("She sounds like a very nice teacher!");
        }else if (statement2.contains("Dr.")) {
            System.out.println("They sound like a great individual!");
        }

    }

    public String getResponse(String statement)
    {
        String response = "";
        statement = Check.removeContractions(statement.toLowerCase().trim());

         if(Check.findKeyword(statement, "hi") >= 0
                || Check.findKeyword(statement, "hello") >= 0
                || Check.findKeyword(statement, "hey") >= 0)
        {
            response = "Hello there.";
        }
        else if(Check.findKeyword(statement, "you") >= 0
                && Check.findKeyword(statement, "how") >= 0)
        {
            response = "I'm doing well.";
        }
        else if(Check.findKeyword(statement, "your") >= 0
                && Check.findKeyword(statement, "name") >= 0)
        {
            response = "I'm Magpie.";
        }
        else if(Check.findKeyword(statement, "no") >= 0)
        {
            response = "Why so negative?";
        }
        else if(Check.findKeyword(statement, "mother") >= 0
                || Check.findKeyword(statement, "father") >= 0
                || Check.findKeyword(statement, "sister") >= 0
                || Check.findKeyword(statement, "brother") >= 0
                || Check.findKeyword(statement, "family") >= 0)
        {
            response = "Tell me more about your family.";
        }
        else if(Check.findKeyword(statement, "dog") >= 0
                || Check.findKeyword(statement, "cat") >= 0 || Check.findKeyword(statement, "pet") >= 0 || Check.findKeyword(statement, "pets") >= 0)
        {
            response = "Tell me more about your pets.";
        }
        else if(Check.findKeyword(statement, "mr.") >= 0)
        {
            response = "He sounds like a good teacher.";
        }
        else if(Check.findKeyword(statement, "computer science") >= 0)
        {
            if(Check.findKeyword(statement, "like") >= 0)
            {
                response = "Yes, it is my favorite course.";
            }
            else
            {
                response = "I love that class.";
            }
        }
        else if(Check.findKeyword(statement, "good") >= 0)
        {
            response = "That's good to hear.";
        }
        else if(Check.findKeyword(statement, "favorite") >= 0)
        {
            if(Check.isQuestion(statement))
            {
                response = "I'm not sure.";
            }
            else
            {
                response = "That's mine, too.";
            }
        }
        else if(Check.findKeyword(statement, "birthday") >= 0)
        {
            response = "Happy birthday!";
        }
        else if(Check.findKeyword(statement, "feared") >= 0)
        {
            response = "FEARED.";
        }
        else if(Check.findKeyword(statement, "questions") >= 0)
        {
            response = "No.";
        }
        
        else if(Check.containsBeVerb(statement)
                && Check.containsSubjectPronoun(statement))
        {
            if(Check.findKeyword(statement, Check.findBeVerb(statement), Check.findKeyword(statement, Check.findSubjectPronoun(statement))) >= 0)
            {
                response = transformSubjectBeVerbStatement(statement);
            }
            else if(Check.findKeyword(statement, Check.findSubjectPronoun(statement), Check.findKeyword(statement, Check.findBeVerb(statement))) >= 0)
            {
                response = transformBeVerbSubjectStatement(statement);
            }
        }
        else if(Check.findKeyword(statement, "i want") >= 0)
        {
            if(Check.findKeyword(statement, "to", Check.findKeyword(statement, "i want")) >= 0)
            {
                response = transformIWantToStatement(statement);
            }
            else
            {
                response = transformIWantStatement(statement);
            }
        }
        else if(Check.containsModalAuxiliary(statement)
                && Check.isQuestion(statement))
        {
            response = transformModalAuxiliaryStatement(statement);
        }
        else if(Check.findKeyword(statement, "i") >= 0
                && Check.findKeyword(statement, "you", Check.findKeyword(statement, "i")) >= 0)
        {
            response = transformIYouStatement(statement);
        }
        else if(Check.findKeyword(statement, "you") >= 0
                && Check.findKeyword(statement, "me", Check.findKeyword(statement, "you")) >= 0)
        {
            response = transformYouMeStatement(statement);
        }
        else if(Check.findKeyword(statement, "you") >= 0
                && Check.findKeyword(statement, "like", Check.findKeyword(statement, "you")) >= 0)
        {
            response = transformYouLikeStatement(statement);
        }
        else if(Check.isQuestion(statement)) {
             response = "I'm not sure.";
         } else {
             response = randomResponse();
         }
        return response;
    }

    private String transformSubjectBeVerbStatement(String statement)
    {
        
        if(statement.charAt(statement.length() - 1) == '?'
                || statement.charAt(statement.length() - 1) == '.')
        {
            statement = statement.substring(0, statement.length() - 1);
        }

        String subject = Check.findSubjectPronoun(statement);
        String beVerb = Check.findBeVerb(statement);
        int positionOfSubject = Check.findKeyword(statement, subject);
        int positionOfBeVerb = Check.findKeyword(statement, beVerb, positionOfSubject);
        String restOfStatement = statement.substring(positionOfBeVerb + beVerb.length()).trim();
        String newStatement = "Why " + beVerb + " " + subject + " " + restOfStatement + "?";

        return Check.invertPointOfView(newStatement);
    }

    private String transformBeVerbSubjectStatement(String statement)
    {
        
        if(statement.charAt(statement.length() - 1) == '?'
                || statement.charAt(statement.length() - 1) == '.')
        {
            statement = statement.substring(0, statement.length() - 1);
        }

        String beVerb = Check.findBeVerb(statement);
        String subject = Check.findSubjectPronoun(statement);
        int positionOfBeVerb = Check.findKeyword(statement, beVerb);
        int positionOfSubject = Check.findKeyword(statement, subject, positionOfBeVerb);
        String restOfStatement = statement.substring(positionOfSubject + subject.length()).trim();
        String newStatement = subject + " " + beVerb + " " + restOfStatement + ".";

        return "I don't know if " + Check.invertPointOfView(newStatement);
    }

    private String transformModalAuxiliaryStatement(String statement)
    {
        
        if(statement.charAt(statement.length() - 1) == '?' || statement.charAt(statement.length() - 1) == '.')
        {
            statement = statement.substring(0, statement.length() - 1);
        }

        String aux = Check.findModalAuxiliary(statement);
        int positionOfAux = Check.findKeyword(statement, aux);
        int positionOfSubject = statement.indexOf(' ', positionOfAux + aux.length() + 1);
        String subject = statement.substring(positionOfAux + aux.length() + 1, positionOfSubject);
        String restOfStatement = statement.substring(positionOfSubject + subject.length()).trim();
        String newStatement = subject + " " + restOfStatement + "?";

        return "I don't know, " + aux + " " + Check.invertPointOfView(newStatement);
    }


    private String transformIWantToStatement(String statement)
    {
      
        if(statement.charAt(statement.length() - 1) == '?'
                || statement.charAt(statement.length() - 1) == '.')
        {
            statement = statement.substring(0, statement.length() - 1);
        }

        int position = Check.findKeyword(statement, "i want to");
        String restOfStatement = statement.substring(position + "i want to".length()).trim();

        return "Why do you want to " + Check.invertPointOfView(restOfStatement) + "?";
    }


    private String transformIWantStatement(String statement)
    {
   
        if(statement.charAt(statement.length() - 1) == '?'
                || statement.charAt(statement.length() - 1) == '.')
        {
            statement = statement.substring(0, statement.length() - 1);
        }

        int position = Check.findKeyword(statement, "i want");
        String restOfStatement = statement.substring(position + "i want".length()).trim();

        return "Would you really be happy if you had " + restOfStatement + "?";
    }


    private String transformYouLikeStatement(String statement)
    {
       
        if(statement.charAt(statement.length() - 1) == '?'
                || statement.charAt(statement.length() - 1) == '.')
        {
            statement = statement.substring(0, statement.length() - 1);
        }

        int positionOfYou = Check.findKeyword(statement, "you");
        int positionOfLike = Check.findKeyword(statement, "like", positionOfYou + "you".length());
        String restOfStatement = statement.substring(positionOfLike + "like".length()).trim();

        return "I'm not sure if I like " + restOfStatement + ".";
    }


    private String transformYouMeStatement(String statement)
    {
        
        if(statement.charAt(statement.length() - 1) == '?'
                || statement.charAt(statement.length() - 1) == '.')
        {
            statement = statement.substring(0, statement.length() - 1);
        }

        int positionOfYou = Check.findKeyword(statement, "you");
        int positionOfMe = Check.findKeyword(statement, "me", positionOfYou + "you".length());
        String restOfStatement = statement.substring(positionOfYou + "you".length(), positionOfMe).trim();
        String endingObjects = statement.substring(positionOfMe + "me".length()).trim();

        return "What makes you think that I " + restOfStatement + " you " + endingObjects + "?";
    }


    private String transformIYouStatement(String statement)
    {
        if(statement.charAt(statement.length() - 1) == '?'
                || statement.charAt(statement.length() - 1) == '.')
        {
            statement = statement.substring(0, statement.length() - 1);
        }

        int positionOfI = Check.findKeyword(statement, "i");
        int positionOfYou = Check.findKeyword(statement, "you", positionOfI);
        String restOfStatement = statement.substring(positionOfI + "i".length(), positionOfYou).trim();

        if(Check.containsBeVerb(statement))
        {
            String newStatement = "Why " + restOfStatement + " me?";
            return Check.invertPointOfView(newStatement);
        }

        return "Why do you " + restOfStatement + " me?";
    }


    public String randomResponse() {
        int random = (int) (Math.random() * 3);

        return switch (random) {
            case 0 -> "Interesting, tell me more.";
            case 1 -> "Hmmm.";
            case 2 -> "Do you really think so?";
            case 3 -> "You don't say.";
            default -> "I don't know what to say.";
        };

    }

}
