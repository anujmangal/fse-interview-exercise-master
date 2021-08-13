package com.tradeledger.cards.common;

import java.util.HashSet;
import java.util.Set;

public final class ApplicantEligibility {

	private Set<String> eligibleCards;

	public ApplicantEligibility(){

	}
	public ApplicantEligibility(Set<String> eligibleCards) {
		
		this.eligibleCards = eligibleCards;
	}

	public static ApplicantEligibilityBuilder newEligibility(final int numberOfEligibleCards) {
		
		return new ApplicantEligibilityBuilder(numberOfEligibleCards);
	}
	
	public static class ApplicantEligibilityBuilder {
		
		private final int numberOfEligibleCards;
		private final Set<String> newEligibility;
		
		public ApplicantEligibilityBuilder(final int numberOfEligibleCards) {
			
			this.numberOfEligibleCards = numberOfEligibleCards;
			this.newEligibility = new HashSet<String>(numberOfEligibleCards);
		}
		
		public ApplicantEligibilityBuilder addCard(final String cardName) {
			
			if (numberOfEligibleCards > newEligibility.size()) {
				newEligibility.add(cardName);
				return this;
			}

			throw new IllegalStateException("Cannot add more cards than eligible for!");
		}
		
		public ApplicantEligibility build() {
			
			if (numberOfEligibleCards != newEligibility.size()) {
				throw new IllegalStateException("Incorrect number of cards specified!");
			}
			
			return new ApplicantEligibility(newEligibility);
		}
	}

	public Set<String> getEligibleCards() {
		return this.eligibleCards;
	}
}
