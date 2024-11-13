const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

export default function useMenu() {
  return{
    handleOpen,
    handleClose
  }
}
