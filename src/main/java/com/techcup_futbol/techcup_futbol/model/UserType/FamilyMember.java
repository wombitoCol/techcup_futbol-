package com.techcup_futbol.techcup_futbol.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "family_members")
public class FamilyMember extends UserProfile {

    @Column(nullable = false)
    private String gmailEmail;

    @Column(nullable = false)
    private String relationship;

    @ManyToOne
    @JoinColumn(name = "related_user_id")
    private UserProfile relatedUser;

    @Override
    public String getAffiliationType() { return "Family Member"; }

    public String getGmailEmail() { return gmailEmail; }
    public void setGmailEmail(String gmailEmail) { this.gmailEmail = gmailEmail; }

    public String getRelationship() { return relationship; }
    public void setRelationship(String relationship) { this.relationship = relationship; }

    public UserProfile getRelatedUser() { return relatedUser; }
    public void setRelatedUser(UserProfile relatedUser) { this.relatedUser = relatedUser; }
}