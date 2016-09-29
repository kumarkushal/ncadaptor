package org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.DataObject;
import java.util.HashMap;
import org.opendaylight.yangtools.concepts.Builder;
import java.util.Collections;
import java.util.Map;


/**
 * Class that builds {@link org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData} instances.
 *
 * @see org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData
 *
 */
public class NodeOperDataBuilder implements Builder <org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData> {

    private java.lang.String _deviceFamily;
    private java.lang.String _deviceName;
    private java.lang.String _deviceOS;
    private java.lang.String _deviceOSVersion;
    private java.lang.String _deviceVendor;
    private java.lang.String _jsonUrl;
    private java.lang.String _yangRevision;
    private java.lang.Boolean _operNode;

    Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>> augmentation = Collections.emptyMap();

    public NodeOperDataBuilder() {
    }

    public NodeOperDataBuilder(NodeOperData base) {
        this._deviceFamily = base.getDeviceFamily();
        this._deviceName = base.getDeviceName();
        this._deviceOS = base.getDeviceOS();
        this._deviceOSVersion = base.getDeviceOSVersion();
        this._deviceVendor = base.getDeviceVendor();
        this._jsonUrl = base.getJsonUrl();
        this._yangRevision = base.getYangRevision();
        this._operNode = base.isOperNode();
        if (base instanceof NodeOperDataImpl) {
            NodeOperDataImpl impl = (NodeOperDataImpl) base;
            if (!impl.augmentation.isEmpty()) {
                this.augmentation = new HashMap<>(impl.augmentation);
            }
        } else if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            AugmentationHolder<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData> casted =(AugmentationHolder<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>) base;
            if (!casted.augmentations().isEmpty()) {
                this.augmentation = new HashMap<>(casted.augmentations());
            }
        }
    }


    public java.lang.String getDeviceFamily() {
        return _deviceFamily;
    }
    
    public java.lang.String getDeviceName() {
        return _deviceName;
    }
    
    public java.lang.String getDeviceOS() {
        return _deviceOS;
    }
    
    public java.lang.String getDeviceOSVersion() {
        return _deviceOSVersion;
    }
    
    public java.lang.String getDeviceVendor() {
        return _deviceVendor;
    }
    
    public java.lang.String getJsonUrl() {
        return _jsonUrl;
    }
    
    public java.lang.String getYangRevision() {
        return _yangRevision;
    }
    
    public java.lang.Boolean isOperNode() {
        return _operNode;
    }
    
    @SuppressWarnings("unchecked")
    public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>> E getAugmentation(java.lang.Class<E> augmentationType) {
        if (augmentationType == null) {
            throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
        }
        return (E) augmentation.get(augmentationType);
    }

    public NodeOperDataBuilder setDeviceFamily(java.lang.String value) {
        this._deviceFamily = value;
        return this;
    }
    
    public NodeOperDataBuilder setDeviceName(java.lang.String value) {
        this._deviceName = value;
        return this;
    }
    
    public NodeOperDataBuilder setDeviceOS(java.lang.String value) {
        this._deviceOS = value;
        return this;
    }
    
    public NodeOperDataBuilder setDeviceOSVersion(java.lang.String value) {
        this._deviceOSVersion = value;
        return this;
    }
    
    public NodeOperDataBuilder setDeviceVendor(java.lang.String value) {
        this._deviceVendor = value;
        return this;
    }
    
    public NodeOperDataBuilder setJsonUrl(java.lang.String value) {
        this._jsonUrl = value;
        return this;
    }
    
    public NodeOperDataBuilder setYangRevision(java.lang.String value) {
        this._yangRevision = value;
        return this;
    }
    
    public NodeOperDataBuilder setOperNode(java.lang.Boolean value) {
        this._operNode = value;
        return this;
    }
    
    public NodeOperDataBuilder addAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>> augmentationType, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData> augmentation) {
        if (augmentation == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    public NodeOperDataBuilder removeAugmentation(java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    public NodeOperData build() {
        return new NodeOperDataImpl(this);
    }

    private static final class NodeOperDataImpl implements NodeOperData {

        public java.lang.Class<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData> getImplementedInterface() {
            return org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData.class;
        }

        private final java.lang.String _deviceFamily;
        private final java.lang.String _deviceName;
        private final java.lang.String _deviceOS;
        private final java.lang.String _deviceOSVersion;
        private final java.lang.String _deviceVendor;
        private final java.lang.String _jsonUrl;
        private final java.lang.String _yangRevision;
        private final java.lang.Boolean _operNode;

        private Map<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>> augmentation = Collections.emptyMap();

        private NodeOperDataImpl(NodeOperDataBuilder base) {
            this._deviceFamily = base.getDeviceFamily();
            this._deviceName = base.getDeviceName();
            this._deviceOS = base.getDeviceOS();
            this._deviceOSVersion = base.getDeviceOSVersion();
            this._deviceVendor = base.getDeviceVendor();
            this._jsonUrl = base.getJsonUrl();
            this._yangRevision = base.getYangRevision();
            this._operNode = base.isOperNode();
            switch (base.augmentation.size()) {
            case 0:
                this.augmentation = Collections.emptyMap();
                break;
            case 1:
                final Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>> e = base.augmentation.entrySet().iterator().next();
                this.augmentation = Collections.<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>>singletonMap(e.getKey(), e.getValue());
                break;
            default :
                this.augmentation = new HashMap<>(base.augmentation);
            }
        }

        @Override
        public java.lang.String getDeviceFamily() {
            return _deviceFamily;
        }
        
        @Override
        public java.lang.String getDeviceName() {
            return _deviceName;
        }
        
        @Override
        public java.lang.String getDeviceOS() {
            return _deviceOS;
        }
        
        @Override
        public java.lang.String getDeviceOSVersion() {
            return _deviceOSVersion;
        }
        
        @Override
        public java.lang.String getDeviceVendor() {
            return _deviceVendor;
        }
        
        @Override
        public java.lang.String getJsonUrl() {
            return _jsonUrl;
        }
        
        @Override
        public java.lang.String getYangRevision() {
            return _yangRevision;
        }
        
        @Override
        public java.lang.Boolean isOperNode() {
            return _operNode;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public <E extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>> E getAugmentation(java.lang.Class<E> augmentationType) {
            if (augmentationType == null) {
                throw new IllegalArgumentException("Augmentation Type reference cannot be NULL!");
            }
            return (E) augmentation.get(augmentationType);
        }

        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int prime = 31;
            int result = 1;
            result = prime * result + ((_deviceFamily == null) ? 0 : _deviceFamily.hashCode());
            result = prime * result + ((_deviceName == null) ? 0 : _deviceName.hashCode());
            result = prime * result + ((_deviceOS == null) ? 0 : _deviceOS.hashCode());
            result = prime * result + ((_deviceOSVersion == null) ? 0 : _deviceOSVersion.hashCode());
            result = prime * result + ((_deviceVendor == null) ? 0 : _deviceVendor.hashCode());
            result = prime * result + ((_jsonUrl == null) ? 0 : _jsonUrl.hashCode());
            result = prime * result + ((_yangRevision == null) ? 0 : _yangRevision.hashCode());
            result = prime * result + ((_operNode == null) ? 0 : _operNode.hashCode());
            result = prime * result + ((augmentation == null) ? 0 : augmentation.hashCode());
        
            hash = result;
            hashValid = true;
            return result;
        }

        @Override
        public boolean equals(java.lang.Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DataObject)) {
                return false;
            }
            if (!org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData.class.equals(((DataObject)obj).getImplementedInterface())) {
                return false;
            }
            org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData other = (org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData)obj;
            if (_deviceFamily == null) {
                if (other.getDeviceFamily() != null) {
                    return false;
                }
            } else if(!_deviceFamily.equals(other.getDeviceFamily())) {
                return false;
            }
            if (_deviceName == null) {
                if (other.getDeviceName() != null) {
                    return false;
                }
            } else if(!_deviceName.equals(other.getDeviceName())) {
                return false;
            }
            if (_deviceOS == null) {
                if (other.getDeviceOS() != null) {
                    return false;
                }
            } else if(!_deviceOS.equals(other.getDeviceOS())) {
                return false;
            }
            if (_deviceOSVersion == null) {
                if (other.getDeviceOSVersion() != null) {
                    return false;
                }
            } else if(!_deviceOSVersion.equals(other.getDeviceOSVersion())) {
                return false;
            }
            if (_deviceVendor == null) {
                if (other.getDeviceVendor() != null) {
                    return false;
                }
            } else if(!_deviceVendor.equals(other.getDeviceVendor())) {
                return false;
            }
            if (_jsonUrl == null) {
                if (other.getJsonUrl() != null) {
                    return false;
                }
            } else if(!_jsonUrl.equals(other.getJsonUrl())) {
                return false;
            }
            if (_yangRevision == null) {
                if (other.getYangRevision() != null) {
                    return false;
                }
            } else if(!_yangRevision.equals(other.getYangRevision())) {
                return false;
            }
            if (_operNode == null) {
                if (other.isOperNode() != null) {
                    return false;
                }
            } else if(!_operNode.equals(other.isOperNode())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                NodeOperDataImpl otherImpl = (NodeOperDataImpl) obj;
                if (augmentation == null) {
                    if (otherImpl.augmentation != null) {
                        return false;
                    }
                } else if(!augmentation.equals(otherImpl.augmentation)) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<java.lang.Class<? extends Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>>, Augmentation<org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ncadaptor.rev150105.get.node.instance.output.NodeOperData>> e : augmentation.entrySet()) {
                    if (!e.getValue().equals(other.getAugmentation(e.getKey()))) {
                        return false;
                    }
                }
                // .. and give the other one the chance to do the same
                if (!obj.equals(this)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public java.lang.String toString() {
            java.lang.StringBuilder builder = new java.lang.StringBuilder ("NodeOperData [");
            boolean first = true;
        
            if (_deviceFamily != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_deviceFamily=");
                builder.append(_deviceFamily);
             }
            if (_deviceName != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_deviceName=");
                builder.append(_deviceName);
             }
            if (_deviceOS != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_deviceOS=");
                builder.append(_deviceOS);
             }
            if (_deviceOSVersion != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_deviceOSVersion=");
                builder.append(_deviceOSVersion);
             }
            if (_deviceVendor != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_deviceVendor=");
                builder.append(_deviceVendor);
             }
            if (_jsonUrl != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_jsonUrl=");
                builder.append(_jsonUrl);
             }
            if (_yangRevision != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_yangRevision=");
                builder.append(_yangRevision);
             }
            if (_operNode != null) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                builder.append("_operNode=");
                builder.append(_operNode);
             }
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append("augmentation=");
            builder.append(augmentation.values());
            return builder.append(']').toString();
        }
    }

}
