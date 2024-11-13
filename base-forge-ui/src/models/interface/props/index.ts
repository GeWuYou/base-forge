/* tabsMenuProps */
export interface TabsMenuProps {
  icon: string
  title: string
  path: string
  name: string
  close: boolean
  isKeepAlive: boolean
}

/* TabsState */
export interface TabsState {
  tabsMenuList: TabsMenuProps[]
}

/* metaProps */
export interface MetaProps {
  icon: string
  title: string
  isLink: boolean
  isEnable: boolean
  isAffix: boolean
  isKeepAlive: boolean
}
