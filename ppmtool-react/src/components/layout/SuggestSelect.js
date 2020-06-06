import React from "react";
import Select from "react-select";

const SuggestSelect = ({options, input}) => (
  <Select
    {...input}
    options={options}
    onChange={option => input.onChange(option)}
    onBlur={() => input.onBlur(input.value)}
  />
);

export const RenderSelectInput = ({input, options, meta: {touched, error}}) => (
  <div className="alert-danger">
    <Select
      {...input}
      onChange={value => input.onChange(value)}
      onBlur={() => input.onBlur(input.value)}
      options={options}
    />
    {touched && (error && <span>{error}</span>)}
  </div>
);

export default SuggestSelect;
