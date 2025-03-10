package model;

public class Candidate {
    private String name;
    private int voteCount;

    public Candidate(String name) {
        this.name = name;
        this.voteCount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void addVote() {
        this.voteCount++;
    }

    @Override
    public String toString() {
        return "Candidate{name='" + name + "', voteCount=" + voteCount + "}";
    }
}
