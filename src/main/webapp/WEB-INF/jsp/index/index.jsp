                <md-toolbar class="md-hue-2 md-whiteframe-1dp">
                    <h1 class="md-toolbar-tools">
                        <span>{{title}}</span>
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
                <md-content flex layout="column" layout-padding md-scroll-y class="md-body-1">
                    <div>
                        <div class="content">
                            <div class="row">
                                <div class="col-xs-3">
                                    <div class="info-box">
                                        <span class="info-box-icon bg-green"><i class="fa fa-paint-brush"></i></span>

                                        <div class="info-box-content">
                                            <span class="info-box-text">Cores</span>
                                            <span class="info-box-number">${totalColors}</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xs-3">
                                    <div class="info-box">
                                        <span class="info-box-icon bg-yellow"><i class="fa fa-car"></i></span>

                                        <div class="info-box-content">
                                            <span class="info-box-text">Veículos</span>
                                            <span class="info-box-number">0</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </md-content>
                <!-- ./content -->