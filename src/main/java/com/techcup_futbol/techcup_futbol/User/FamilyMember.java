package com.techcup_futbol.techcup_futbol.User;

public class FamilyMember extends User {

    private String gmailEmail;
    private String relationship;
    private User relatedUser;

    @Override
    public String getAffiliationType() { return "Family Member"; }

    public String getGmailEmail() { return gmailEmail; }
    public void setGmailEmail(String gmailEmail) { this.gmailEmail = gmailEmail; }

    public String getRelationship() { return relationship; }
    public void setRelationship(String relationship) { this.relationship = relationship; }

    public User getRelatedUser() { return relatedUser; }
    public void setRelatedUser(User relatedUser) { this.relatedUser = relatedUser; }
}