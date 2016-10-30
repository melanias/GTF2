                <md-toolbar class="md-hue-2 md-whiteframe-1dp">
                    <h1 class="md-toolbar-tools">
                        <span>{{title}}</span>
                        <md-icon class="icon-32">chevron_right</md-icon>
                        <span flex class="md-subhead">{{subTitle}}</span>
                        <md-menu>
                            <md-button class="md-icon-button" ng-click="$mdOpenMenu($event)">
                                <md-icon>more_vert</md-icon>
                            </md-button>
                            <md-menu-content width="4">
                                <md-menu-item>
                                    <md-button href="logout">
                                        <md-icon>exit_to_app</md-icon>
                                        Sair
                                    </md-button>
                                </md-menu-item>
                            </md-menu-content>
                        </md-menu>
                    </h1>
                </md-toolbar>

                <!-- content -->
                <md-content flex layout="column" layout-margin md-scroll-y class="md-body-1" ui-view></md-button>
                </md-content>
                <!-- ./content -->