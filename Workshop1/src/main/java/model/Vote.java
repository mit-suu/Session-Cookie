package model;

import java.util.ArrayList;
import java.util.List;

public class Vote {
    private String title;
    private List<Candidate> candidates;

    public Vote(String title) {
        this.title = title;
        this.candidates = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void addCandidate(String candidateName) {
        candidates.add(new Candidate(candidateName));
    }

    public void voteCandidate(String candidateName) {
        for (Candidate c : candidates) {
            if (c.getName().equals(candidateName)) {
                c.addVote();
                break;
            }
        }
    }

    public void showResults() {
        System.out.println("Voting Results for: " + title);
        for (Candidate c : candidates) {
            System.out.println(c.getName() + ": " + c.getVoteCount() + " votes");
        }
    }
}
