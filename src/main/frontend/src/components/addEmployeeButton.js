import { AddEmployeeIcon } from "../icons"

import "../styles/styles.css"

export default function AddEmployeeButton({ checked, colorOff, colorOn, ...props }) {
	return (
		
			<input className={styles.input} type={"checkbox"} checked={checked} {...props} />

	)
}