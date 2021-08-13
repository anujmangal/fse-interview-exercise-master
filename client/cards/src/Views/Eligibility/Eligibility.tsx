import React, {useState} from "react";
import View from "../../DesignSystem/View";
import EligibilityApplication from "./EligibilityApplication";
import EligibilityResults from "./EligibilityResults";

const Eligibility = () => {
  const [cardsAllowed, setCardAllowed] = useState('');

  const onSubmitHandler = (cardsAllowed: string) => {
    setCardAllowed(cardsAllowed);
  }

  return (
    <View>
      <EligibilityApplication submitFunction={onSubmitHandler} />
      <EligibilityResults cardsAllowed={cardsAllowed}/>
    </View>
  );
};

export default Eligibility;
